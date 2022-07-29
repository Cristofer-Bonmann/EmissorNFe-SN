package com.vertyce.nfe.icms;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import com.vertyce.enums.EModBC;
import com.vertyce.nfe.DetUtil;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.Objects;

public class GeradorICMS00 implements IGeradorICMS00 {
    /**
     * Gera as informações para a tributação do ICMS 00. <br>
     *
     * 1. o objeto Imposto deve ter pelo menos um 'item' na sua lista de Content(lista JAXBElement); <br>
     * 2. percorre os itens retornados pelo 'getContent' (Lista de JAXBElement) e retorna o objeto do tipo ICMS, se
     * nenhum 'item' desse tipo for encontrado, retorna-se nulo;  <br>
     * 3. Converte o objeto do tipo ICMS para a classe ICMS;  <br>
     * 4. cria uma instância do tipo ICMS00, atribuirá valores em suas propriedades;  <br>
     * 5. atribuí o objeto ICMS00 no objeto ICMS.  <br>
     *
     * @param infNFe objeto InfNFe.
     */
    @Override
    public void geraICMS00(TNFe.InfNFe infNFe) {
        final List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream()
                .filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> imposto.getContent().size() >= 1)

                .map(imposto -> DetUtil.getValueDoJAXBElement(imposto.getContent(), ICMS.class))
                .filter(Objects::nonNull)
                .map(valueICMS -> {
                    final ICMS icms = (ICMS) valueICMS;
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
