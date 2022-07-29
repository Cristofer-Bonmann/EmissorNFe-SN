package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;

import java.util.List;

public class GeradorICMS implements IGeradorICMS {

    /**
     * Gera informações da tributação ICMS.
     * @param infNFe
     */
    @Override
    public void gerarICMS(InfNFe infNFe) {
        List<Det> dets = infNFe.getDet();
        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .forEach(imposto -> {
                    final ICMS icms = new ICMS();
                    imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
                });
    }
}
