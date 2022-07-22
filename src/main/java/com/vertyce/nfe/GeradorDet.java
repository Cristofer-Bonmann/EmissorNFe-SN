package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

public class GeradorDet implements IGeradorDet {
    /**
     * Gera detalhamento dos produtos e serviços da nota fiscal.
     * @param infNFe TAG raiz da NF-e.
     */
    @Override
    public void gerarDet(TNFe.InfNFe infNFe) {
        final Integer nItem = 1;

        final TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
        det.setNItem(String.valueOf(nItem));

        infNFe.getDet().add(det);
    }
}
