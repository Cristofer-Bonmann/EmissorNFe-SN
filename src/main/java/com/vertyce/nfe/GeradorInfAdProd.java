package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

import java.util.List;

public class GeradorInfAdProd implements IGeradorInfAdProd {
    // TODO: 26/07/2022 inserir doc
    @Override
    public void gerarInfAdProd(TNFe.InfNFe infNFe) {
        List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream().forEach(det -> det.setInfAdProd("INFORMAÇÃO ADICIONAL - HOMOLOGAÇÃO"));
    }
}
