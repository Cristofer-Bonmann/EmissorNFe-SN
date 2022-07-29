package com.vertyce.nfe;


import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.stream.Stream;

public class DetUtil {

    /**
     * Faz um Stream de Det -> Imposto (filtrando imposto diferente de nulo) -> Content (lista JAXBElement diferente de vazia).
     * @param streamDet um Stream do tipo Det.
     * @return um Stream de lista do tipo JAXBElement.
     */
    public static Stream<List<JAXBElement<?>>> getStreamDetImpostoContent(Stream<Det> streamDet){
        return streamDet.filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> !imposto.getContent().isEmpty())
                .map(imposto -> imposto.getContent());
    }
}
