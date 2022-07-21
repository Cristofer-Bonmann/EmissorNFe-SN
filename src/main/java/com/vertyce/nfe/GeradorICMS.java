package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

import java.util.List;

public class GeradorICMS implements GeradorICMSPresenter {

    // TODO: 20/07/2022 inserir doc
    @Override
    public void gerarICMS(TNFe.InfNFe infNFe) {
        List<TNFe.InfNFe.Det> dets = infNFe.getDet();
        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .forEach(imposto -> {
                    TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
                    imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
                });
    }
}
