package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import com.vertyce.enums.EICMSTotMethod;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class GeradorICMSTot implements IGeradorICMSTot{

    // TODO: 28/07/2022 inserir doc
    private Object getValorFieldPorNome(Prod prod, String nomeField) throws NoSuchMethodException,
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

    protected String getTotalPorCampo(List<Det> dets, String nomeCampo){

        final BigDecimal bgZero = new BigDecimal("0.00");
        BigDecimal totalCampo;
        String strTotalCampo = "0.00";

        boolean methodExiste = methodExiste(nomeCampo);

        if (methodExiste) {
            if (!dets.isEmpty()) {
                totalCampo = dets.stream()
                        .filter(det -> det.getProd() != null)
                        .map(det -> det.getProd())
                        .map(prod -> {
                            Object valorFieldPorNome;
                            BigDecimal bgValorCampo = bgZero;

                            try {
                                valorFieldPorNome = getValorFieldPorNome(prod, nomeCampo);
                            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                valorFieldPorNome = null;
                            }

                            if (valorFieldPorNome != null) {
                                try {
                                    bgValorCampo = new BigDecimal(String.valueOf(valorFieldPorNome));
                                } catch (NumberFormatException nfe) {
                                    System.err.println(nfe.getMessage());
                                }
                            }
                            return bgValorCampo;

                        })
                        .reduce(BigDecimal::add)
                        .orElse(bgZero);

                strTotalCampo = String.valueOf(totalCampo);
            }
        } else {
            strTotalCampo = null;
        }

        return strTotalCampo;
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

            icmsTot.setVBC(vBC);
            icmsTot.setVICMS(vICMS);
            icmsTot.setVICMSDeson(vICMSDeson);
            icmsTot.setVBCST(vBCST);
            icmsTot.setVST(vST);
            icmsTot.setVProd(vProd);
            icmsTot.setVFrete(vFrete);
        }
    }
}
