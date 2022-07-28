package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class GeradorICMSTot implements IGeradorICMSTot{

    // TODO: 28/07/2022 inserir doc
    private Object getValorFieldPorNome(Prod prod, String nomeField){
        Object objectValue;

        final String nome = "get" + nomeField;
        try {
            final Method declaredMethod = Prod.class.getDeclaredMethod(nome);
            objectValue = declaredMethod.invoke(prod);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return objectValue;
    }

    protected String getTotalPorCampo(List<Det> dets, String nomeCampo){
        final BigDecimal bgZero = new BigDecimal("0.00");
        BigDecimal totalCampo;
        String strTotalCampo = "0.00";

        if (!dets.isEmpty()) {
            totalCampo = dets.stream()
                    .filter(det -> det.getProd() != null)
                    .map(det -> det.getProd())

                    .map(prod -> {
                        BigDecimal bgValorCampo = bgZero;
                        final Object valorFieldPorNome = getValorFieldPorNome(prod, nomeCampo);
                        if (valorFieldPorNome != null) {
                            try {
                                bgValorCampo = new BigDecimal(String.valueOf(valorFieldPorNome));
                            } catch(NumberFormatException nfe){
                                System.err.println(nfe.getMessage());
                            }
                        }
                        return bgValorCampo;

                    })
                    .reduce(BigDecimal::add)
                    .orElse(bgZero);

            strTotalCampo = String.valueOf(totalCampo);
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
            final String vProd = getTotalPorCampo(infNFe.getDet(), "VProd");
            final String vFrete = getTotalPorCampo(infNFe.getDet(), "VFrete");

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
