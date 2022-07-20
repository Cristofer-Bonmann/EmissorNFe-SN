package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

import java.util.List;

public class GeradorImposto implements GeradorImpostoPresenter{

    // TODO: 20/07/2022 inserir doc
    @Override
    public void gerarImposto(TNFe.InfNFe infNFe) {
        List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream().forEach(det -> det.setImposto(new TNFe.InfNFe.Det.Imposto()));
    }
}
