package com.vertyce.nfe.icms;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import com.vertyce.enums.EModBC;

import java.util.List;

public class GeradorICMS00 implements IGeradorICMS00 {
    /**
     * Gera os informações para a tributação do ICMS 00. <br>
     * O objeto Imposto deve ter pelo menos um item na sua lista de Content(lista JAXBElement); <br>
     * O valor do primeiro item do Content (lista JAXBElement) do Imposto deve ser diferente de null e deve ser do tipo
     * TNFe.InfNFe.Det.Imposto.ICMS<br>
     * @param infNFe
     */
    @Override
    public void geraICMS00(TNFe.InfNFe infNFe) {
        final List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> imposto.getContent().size() >= 1)
                .filter(imposto -> imposto.getContent().get(0).getValue() != null)
                .map(imposto -> {
                    Object valueIcms = imposto.getContent().get(0).getValue();
                    return valueIcms;

                })
                .filter(valueIcms -> valueIcms instanceof ICMS)
                .map(valueIcms -> {
                    ICMS icms = (ICMS) valueIcms;
                    return icms;

                }).forEach(icms -> {
                    final String orig = "0";
                    final String cst = "00";
                    final String modBC = EModBC.VALOR_OPERACAO.getCodigo();
                    final String vBC = "100.00";
                    final String pICMS = "10.0000";
                    final String vICMS = "90.00";

                    ICMS.ICMS00 icms00 = new ICMS.ICMS00();
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
