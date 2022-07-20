package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

public class GeradorDest implements com.vertyce.nfe.GeradorDestPresenter {
    // TODO: 20/07/2022 inserir o doc
    @Override
    public void gerarDest(TNFe.InfNFe infNFe) {
        final TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();
        infNFe.setDest(dest);
    }
}
