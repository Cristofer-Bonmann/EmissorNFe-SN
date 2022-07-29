package com.vertyce.nfe.icmssn;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101;
import com.vertyce.nfe.DetUtil;

import java.util.List;
import java.util.Objects;

public class GeradorICMSSN101 implements IGeradorICMSSN101 {
    // TODO: 21/07/2022 inserir doc
    @Override
    public void gerarICMSSN101(TNFe.InfNFe infNFe) {
        List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> imposto.getContent().size() >= 1)

                .map(imposto -> imposto.getContent())
                .map(jaxbElements -> DetUtil.getValueDoJAXBElement(jaxbElements, ICMS.class))
                .filter(Objects::nonNull)
                .map(valueIcms -> {
                    ICMS icms = (ICMS) valueIcms;
                    return icms;

                }).forEach(icms -> {
                    final String orig = "0";
                    final String csosn = "101";
                    final String pCredSN = "10.0000";
                    final String vCredICMSSN = "10.00";

                    final ICMSSN101 icmssn101 = new ICMSSN101();
                    icms.setICMSSN101(icmssn101);

                    icmssn101.setOrig(orig);
                    icmssn101.setCSOSN(csosn);
                    icmssn101.setPCredSN(pCredSN);
                    icmssn101.setVCredICMSSN(vCredICMSSN);
                });
    }
}
