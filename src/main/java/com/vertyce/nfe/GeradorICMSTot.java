package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class GeradorICMSTot implements IGeradorICMSTot{

    // TODO: 28/07/2022 inserir doc
    protected String getVProd(List<Det> dets){
        final BigDecimal bgZero = new BigDecimal("0.00");
        BigDecimal totalVProd;
        String strTotalVProd = "0.00";

        if (!dets.isEmpty()) {
            totalVProd = dets.stream()
                    .filter(det -> det.getProd() != null)
                    .map(det -> det.getProd()).map(Det.Prod::getVProd)
                    .map(vProd -> {
                        BigDecimal bgVProd = bgZero;
                        if (vProd != null) {
                            try {
                                bgVProd = new BigDecimal(vProd);
                            } catch (NumberFormatException nfe){
                                System.err.println(nfe.getMessage());
                            }
                        }

                        return bgVProd;
                    })
                    .reduce(BigDecimal::add)
                    .orElse(bgZero);

            strTotalVProd = String.valueOf(totalVProd);
        }

        return strTotalVProd;
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
            final String vProd = getVProd(infNFe.getDet());

            icmsTot.setVBC(vBC);
            icmsTot.setVICMS(vICMS);
            icmsTot.setVICMSDeson(vICMSDeson);
            icmsTot.setVBCST(vBCST);
            icmsTot.setVST(vST);
            icmsTot.setVProd(vProd);
        }
    }
}
