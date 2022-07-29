package com.vertyce.nfe.pis;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;

import java.util.List;

public class GeradorPIS implements IGeradorPIS {
    /**
     * Adiciona objeto PIS em InfNFe -> Det -> Imposto. Para todos os itens da lista de Det.
     * @param infNFe objeto InfNFe.
     */
    @Override
    public void gerarPIS(TNFe.InfNFe infNFe) {
        final List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .forEach(imposto -> {
                    PIS pis = new PIS();
                    imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(pis));
                });
    }
}
