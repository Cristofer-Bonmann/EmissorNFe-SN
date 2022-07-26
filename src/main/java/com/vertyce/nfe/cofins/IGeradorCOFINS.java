package com.vertyce.nfe.cofins;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;

public interface IGeradorCOFINS {

    void gerarCOFINS(InfNFe infNFe);
}
