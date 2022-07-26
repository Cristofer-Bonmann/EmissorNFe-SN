package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;

import java.util.List;
import java.util.Objects;

public class GeradorICMSTot implements IGeradorICMSTot{
    // TODO: 26/07/2022 inserir doc
    @Override
    public void gerarICMSTot(InfNFe infNFe) {
        if (infNFe.getTotal() != null){
            final ICMSTot icmsTot = new ICMSTot();
            infNFe.getTotal().setICMSTot(icmsTot);

            final String vBC = "0.00";

            if (!infNFe.getDet().isEmpty()) {
                final ICMS icms = (ICMS) infNFe.getDet().get(0).getImposto().getContent().get(0).getValue();
                final ICMS.ICMSSN101 icmssn101 = icms.getICMSSN101();
            }

            icmsTot.setVBC(vBC);
        }
    }
}
