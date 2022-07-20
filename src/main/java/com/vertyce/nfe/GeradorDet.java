package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

public class GeradorDet implements GeradorDetPresenter{
    // TODO: 20/07/2022 inserir doc
    @Override
    public void gerarDet(TNFe.InfNFe infNFe) {
        final Integer nItem = 1;

        final TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
        det.setNItem(String.valueOf(nItem));

        infNFe.getDet().add(det);
    }
}
