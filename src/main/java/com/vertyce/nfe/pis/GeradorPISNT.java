package com.vertyce.nfe.pis;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import com.vertyce.nfe.DetUtil;

import java.util.List;
import java.util.Objects;

public class GeradorPISNT implements IGeradorPISNT {
    // TODO: 29/07/2022 inserir doc
    @Override
    public void gerarPISNT(TNFe.InfNFe infNFe) {
        final List<Det> dets = infNFe.getDet();

        DetUtil.getStreamDetImpostoContent(dets.stream())
                .map(jaxbElements -> {
                    Object objectPIS = jaxbElements.stream()
                            .filter(jaxb -> jaxb.getDeclaredType().equals(PIS.class))
                            .map(jaxb -> jaxb.getValue())
                            .findFirst().orElse(null);
                    return objectPIS;
                })

                .filter(Objects::nonNull)
                .map(valuePis -> {
                    final PIS pis = (PIS) valuePis;
                    return pis;

                }).forEach(pis -> {
                    final String cst = "04";

                    final PISNT pisnt = new PISNT();
                    pis.setPISNT(pisnt);

                    pisnt.setCST(cst);
                });
    }
}
