package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;

public class GeradorTotal implements IGeradorTotal {
    // TODO: 26/07/2022 inserir doc
    @Override
    public void gerarTotal(TNFe.InfNFe infNFe) {
        infNFe.setTotal(new Total());
    }
}
