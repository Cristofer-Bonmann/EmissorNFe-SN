package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

public interface GeradorDestPresenter {

    void gerarDest(TNFe.InfNFe infNFe);

    void setView(DestinatarioView view);
}
