package com.vertyce.nfe.pis;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;

import java.util.List;

public class GeradorPISNT implements IGeradorPISNT {
    // TODO: 22/07/2022 inserir doc
    @Override
    public void gerarPisNT(TNFe.InfNFe infNFe) {
        final List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> imposto.getContent().size() >= 1)
                .filter(imposto -> imposto.getContent().get(0).getValue() != null)
                .map(imposto -> {
                    Object valueIcms = imposto.getContent().get(0).getValue();
                    return valueIcms;

                })
                .filter(valuePis -> valuePis instanceof PIS)
                .map(valuePis -> {
                    final PIS pis = (TNFe.InfNFe.Det.Imposto.PIS) valuePis;
                    return pis;

                }).forEach(pis -> {
                    final PIS.PISNT pisnt = new PIS.PISNT();

                    pis.setPISNT(pisnt);
                });
    }
}
