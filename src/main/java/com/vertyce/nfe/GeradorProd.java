package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

import java.util.List;

public class GeradorProd implements GeradorProdPresenter{
    // TODO: 20/07/2022 inserir doc
    @Override
    public void gerarProd(TNFe.InfNFe infNFe) {
        List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream().forEach(det -> {
            final TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
            det.setProd(prod);
        });
    }
}
