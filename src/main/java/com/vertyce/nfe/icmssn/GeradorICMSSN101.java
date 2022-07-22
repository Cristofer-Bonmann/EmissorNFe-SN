package com.vertyce.nfe.icmssn;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101;
import com.vertyce.enums.EModBC;

import java.util.List;

public class GeradorICMSSN101 implements GeradorICMSSN101Presenter {
    // TODO: 21/07/2022 inserir doc
    @Override
    public void gerarICMSSN101(TNFe.InfNFe infNFe) {
        List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> imposto.getContent().size() >= 1)
                .filter(imposto -> imposto.getContent().get(0).getValue() != null)
                .map(imposto -> {
                    Object valueIcms = imposto.getContent().get(0).getValue();
                    return valueIcms;

                })
                .filter(valueIcms -> valueIcms instanceof ICMS)
                .map(valueIcms -> {
                    ICMS icms = (ICMS) valueIcms;
                    return icms;

                }).forEach(icms -> {
                    final String orig = "0";
                    final String cst = "00";
                    final String modBC = EModBC.VALOR_OPERACAO.getCodigo();
                    final String vBC = "100.00";
                    final String pICMS = "10.0000";
                    final String vICMS = "90.00";

                    final ICMSSN101 icmssn101 = new ICMSSN101();
                    icms.setICMSSN101(icmssn101);

//                    icmssn101.setOrig(orig);
//                    icmssn101.setCST(cst);
//                    icmssn101.setModBC(modBC);
//                    icmssn101.setVBC(vBC);
//                    icmssn101.setPICMS(pICMS);
//                    icmssn101.setVICMS(vICMS);
                });
    }
}
