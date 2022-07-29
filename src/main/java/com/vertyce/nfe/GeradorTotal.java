package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;

public class GeradorTotal implements IGeradorTotal {
    /**
     * Adiciona objeto Total na InfNFe,
     * @param infNFe objeto InfNFe.
     */
    @Override
    public void gerarTotal(TNFe.InfNFe infNFe) {
        infNFe.setTotal(new Total());
    }
}
