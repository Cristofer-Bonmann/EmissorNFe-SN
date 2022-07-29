package com.vertyce.nfe;


import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.stream.Stream;

public class DetUtil {

    /**
     * Percorre uma lista do tipo JAXBElement filtrando por um tipo específico de classe e retorna sua propriedade 'value'. <br>
     * Se nenhum 'item' do tipo da classe especificada for encontrada, o retorno será nulo.
     * @param list lista JAXBElement.
     * @param aClass tipo da classe para ser filtrada.
     * @return objeto 'value' de um 'JAXBElement'.
     */
    public static Object getValueDoJAXBElement(List<JAXBElement<?>> list, Class<?> aClass) {
        Object value = list.stream()
                .filter(jaxb -> jaxb.getDeclaredType().equals(aClass))
                .map(jaxb -> jaxb.getValue())
                .findFirst().orElse(null);
        return value;
    }

    /**
     * Faz um Stream de Det -> Imposto (filtrando imposto diferente de nulo) -> Content (lista JAXBElement diferente de vazia).
     *
     * @param streamDet um Stream do tipo Det.
     * @return um Stream de lista do tipo JAXBElement.
     */
    public static Stream<List<JAXBElement<?>>> getStreamDetImpostoContent(Stream<Det> streamDet) {
        return streamDet.filter(det -> det.getImposto() != null)
                .map(det -> det.getImposto())
                .filter(imposto -> !imposto.getContent().isEmpty())
                .map(imposto -> imposto.getContent());
    }
}
