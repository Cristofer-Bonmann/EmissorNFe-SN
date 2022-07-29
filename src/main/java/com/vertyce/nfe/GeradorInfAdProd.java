package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

import java.util.List;

public class GeradorInfAdProd implements IGeradorInfAdProd {
    /**
     * Adiciona informação adicional do produto em InfNFe -> Det. Para todos os itens da lista de Det.
     * @param infNFe objeto InfNFe.
     */
    @Override
    public void gerarInfAdProd(TNFe.InfNFe infNFe) {
        List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream().forEach(det -> det.setInfAdProd("INFORMAÇÃO ADICIONAL - HOMOLOGAÇÃO"));
    }
}
