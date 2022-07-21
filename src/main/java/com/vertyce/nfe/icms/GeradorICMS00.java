package com.vertyce.nfe.icms;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import com.vertyce.enums.EModBC;

import javax.xml.bind.JAXBElement;
import java.util.List;

public class GeradorICMS00 implements com.vertyce.nfe.icms.GeradorICMS00Presenter {
    // TODO: 20/07/2022 inserir doc
    @Override
    public void geraICMS00(TNFe.InfNFe infNFe) {
        List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .map(imposto -> {
                    Object valueIcms = imposto.getContent().get(0).getValue();
                    TNFe.InfNFe.Det.Imposto.ICMS icms = (TNFe.InfNFe.Det.Imposto.ICMS) valueIcms;
                    return icms;

                }).forEach(icms -> {
                    final String orig = "0";
                    final String cst = "00";
                    final String modBC = EModBC.VALOR_OPERACAO.getCodigo();
                    final String vBC = "100.00";
                    final String pICMS = "10.0000";
                    final String vICMS = "90.00";

                    TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS00();
                    icms.setICMS00(icms00);

                    icms00.setOrig(orig);
                    icms00.setCST(cst);
                    icms00.setModBC(modBC);
                    icms00.setVBC(vBC);
                    icms00.setPICMS(pICMS);
                    icms00.setVICMS(vICMS);
                });
    }
}
