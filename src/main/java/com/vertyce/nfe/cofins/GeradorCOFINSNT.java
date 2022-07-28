package com.vertyce.nfe.cofins;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;

import java.util.List;
import java.util.Objects;

public class GeradorCOFINSNT implements IGeradorCOFINSNT{
    // TODO: 26/07/2022 inserir doc
    @Override
    public void gerarCOFINSNT(TNFe.InfNFe infNFe) {
        final List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> !imposto.getContent().isEmpty())
                .map(imposto -> imposto.getContent())

                .map(jaxbElements -> {
                    Object objectCofins = jaxbElements.stream()
                            .filter(jaxb -> jaxb.getDeclaredType().equals(COFINS.class))
                            .map(jaxb -> jaxb.getValue())
                            .findFirst().orElse(null);
                    return objectCofins;
                })

                .filter(Objects::nonNull)
                .map(valueCofins -> {
                    final COFINS cofins = (COFINS) valueCofins;
                    return cofins;

                }).forEach(cofins -> {
                    final String cst = "04";

                    final TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT cofinsnt = new TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT();
                    cofins.setCOFINSNT(cofinsnt);

                    cofinsnt.setCST(cst);
                });
    }
}