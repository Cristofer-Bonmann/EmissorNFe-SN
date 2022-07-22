package com.vertyce.nfe.pis;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;

import java.util.List;
import java.util.Objects;

public class GeradorPISNT implements IGeradorPISNT {
    // TODO: 22/07/2022 inserir doc
    @Override
    public void gerarPISNT(TNFe.InfNFe infNFe) {
        final List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> !imposto.getContent().isEmpty())
                .map(imposto -> imposto.getContent())

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
                    final PIS.PISNT pisnt = new PIS.PISNT();

                    pis.setPISNT(pisnt);
                });
    }
}
