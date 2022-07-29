package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import com.vertyce.enums.EICMSTotMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class GeradorICMSTot implements IGeradorICMSTot{

    // TODO: 28/07/2022 inserir doc
    private Object getValorMethdodInvoke(Prod prod, String nomeField) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        Object objectValue;

        final String nome = "get" + nomeField;
        final Method declaredMethod = Prod.class.getDeclaredMethod(nome);
        objectValue = declaredMethod.invoke(prod);
        return objectValue;
    }

    // TODO: 29/07/2022 inserir doc
    private boolean methodExiste(String nomeMethod){
        boolean existeMethod = true;
        final String nome = "get" + nomeMethod;
        try {
            final Method declaredMethod = Prod.class.getDeclaredMethod(nome);
        } catch (NoSuchMethodException e) {
            existeMethod = false;
        }

        return existeMethod;
    }

    // TODO: 29/07/2022 inserir doc
    protected String getTotalPorCampo(List<Det> dets, String nomeMethod){

        final BigDecimal bgZero = new BigDecimal("0.00");
        BigDecimal total;
        String strTotal = "0.00";

        boolean methodExiste = methodExiste(nomeMethod);

        if (methodExiste) {
            if (!dets.isEmpty()) {
                total = dets.stream()
                        .filter(det -> det.getProd() != null)
                        .map(det -> det.getProd())
                        .map(prod -> {
                            Object valor;
                            BigDecimal bgValor = bgZero;

                            try {
                                valor = getValorMethdodInvoke(prod, nomeMethod);
                                bgValor = new BigDecimal(String.valueOf(valor));

                            } catch (NumberFormatException nfe) {
                                System.err.println(nfe.getMessage());
                            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                System.err.println(e.getMessage());
                            }

                            return bgValor;

                        })
                        .reduce(BigDecimal::add)
                        .orElse(bgZero);

                strTotal = String.valueOf(total);
            }
        } else {
            strTotal = null;
        }

        return strTotal;
    }

    // TODO: 29/07/2022 inserir doc
    protected String getTotalVPIS(List<Det> dets){
        final BigDecimal totalVPis = dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> !imposto.getContent().isEmpty())
                .map(imposto -> imposto.getContent())

                .map(jaxbElements -> {
                    Object objectPIS = jaxbElements.stream()
                            .filter(jaxb -> jaxb.getDeclaredType().equals(Det.Imposto.PIS.class))
                            .map(jaxb -> jaxb.getValue())
                            .findFirst().orElse(null);
                    return objectPIS;
                })

                .filter(Objects::nonNull)
                .map(valuePis -> {
                    final Det.Imposto.PIS pis = (Det.Imposto.PIS) valuePis;
                    return pis;

                })

                .map(pis -> new BigDecimal(pis.getPISAliq().getVPIS()))
                .reduce(BigDecimal::add)
                .orElse(null);

        return String.valueOf(totalVPis);
    }

    // TODO: 26/07/2022 inserir doc
    @Override
    public void gerarICMSTot(InfNFe infNFe) {

        if (infNFe.getTotal() != null){

            final ICMSTot icmsTot = new ICMSTot();
            infNFe.getTotal().setICMSTot(icmsTot);

            final String vBC = "0.00";
            final String vICMS = "0.00";
            final String vICMSDeson = "0.00";
            final String vBCST = "0.00";
            final String vST = "0.00";
            final String vProd = getTotalPorCampo(infNFe.getDet(), EICMSTotMethod.VPROD.getNomeMethod());
            final String vFrete = getTotalPorCampo(infNFe.getDet(), EICMSTotMethod.VFRETE.getNomeMethod());
            final String vSeg = getTotalPorCampo(infNFe.getDet(), EICMSTotMethod.VSEG.getNomeMethod());
            final String vDesc = getTotalPorCampo(infNFe.getDet(), EICMSTotMethod.VDESC.getNomeMethod());
            final String vII = "0.00";
            final String vIPI = "0.00";
            final String vPIS = getTotalVPIS(infNFe.getDet());

            icmsTot.setVBC(vBC);
            icmsTot.setVICMS(vICMS);
            icmsTot.setVICMSDeson(vICMSDeson);
            icmsTot.setVBCST(vBCST);
            icmsTot.setVST(vST);
            icmsTot.setVProd(vProd);
            icmsTot.setVFrete(vFrete);
            icmsTot.setVSeg(vSeg);
            icmsTot.setVDesc(vDesc);
            icmsTot.setVII(vII);
            icmsTot.setVIPI(vIPI);
            icmsTot.setVPIS(vPIS);
        }
    }
}
