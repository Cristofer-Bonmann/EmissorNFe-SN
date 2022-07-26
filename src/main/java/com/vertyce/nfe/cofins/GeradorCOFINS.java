package com.vertyce.nfe.cofins;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

import java.util.List;

public class GeradorCOFINS implements com.vertyce.nfe.cofins.IGeradorCOFINS{
    // TODO: 26/07/2022 inserir doc
    @Override
    public void gerarCOFINS(TNFe.InfNFe infNFe) {
        final List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .forEach(imposto -> {
                    final TNFe.InfNFe.Det.Imposto.COFINS cofins = new TNFe.InfNFe.Det.Imposto.COFINS();
                    imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(cofins));
                });
    }
}
