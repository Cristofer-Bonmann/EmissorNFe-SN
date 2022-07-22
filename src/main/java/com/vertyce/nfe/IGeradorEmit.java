package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

public interface IGeradorEmit {

    void gerarEmit(TNFe.InfNFe infNFe);

    void setView(EmitenteView view);
}
