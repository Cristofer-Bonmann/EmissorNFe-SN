package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;

public class GeradorICMSTot implements IGeradorICMSTot{
    // TODO: 26/07/2022 inserir doc
    @Override
    public void gerarICMSTot(InfNFe infNFe) {
        if (infNFe.getTotal() != null)
            infNFe.getTotal().setICMSTot(new ICMSTot());
    }
}
