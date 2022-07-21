package com.vertyce.util;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;

import javax.xml.bind.JAXBElement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Util {

    /**
     * Adiciona um novo objeto ICMS na Content ICMS, do objeto Imposto, do primeiro item Det do parâmetro InfNFe.
     * @param infNFe
     */
    public static void addICMS(InfNFe infNFe){
        infNFe.getDet().get(0)
                .getImposto().getContent()
                .add(new ObjectFactory()
                        .createTNFeInfNFeDetImpostoICMS(new TNFe.InfNFe.Det.Imposto.ICMS()));
    }

    /**
     * Captura o primeiro item da lista de Det's do parâmetro InfNFe; <br>
     * Captura o objeto imposto desse item; <br>
     * Captura a lista do tipo JAXBElement desse Imposto; <br>
     * Dessa lista é retornado o valor do primeiro item convertido em um objeto ICMS.
     * @param infNFe
     * @return objeto ICMS.
     */
    public static InfNFe.Det.Imposto.ICMS getICMS(InfNFe infNFe){
        List<JAXBElement<?>> jaxeICMS = infNFe.getDet().get(0).getImposto().getContent();
        return (InfNFe.Det.Imposto.ICMS) jaxeICMS.get(0).getValue();
    }

    /**
     * Cria um novo objeto InfNFe, cria um novo objeto Det e inseri ele objeto na lista de det's do InfNFe.
     * No Det criado, será adicionado um novo objeto Imposto.
     * @return novo InfNFe.
     */
    public static InfNFe getInfNFeComImposto(){
        final InfNFe infNFe = new InfNFe();
        InfNFe.Det det = new InfNFe.Det();
        det.setImposto(new InfNFe.Det.Imposto());
        infNFe.getDet().add(det);

        return infNFe;
    }

    /**
     * Converte uma data/hora em um objeto LocalDateTime. Ex.: 2022-01-01 12:30:00
     * @param strLocalDateTime String que será convertida.
     * @return objeto LocalTimeDate da String convertida.
     */
    public static LocalDateTime strToLocalDateTime(String strLocalDateTime){
        final String pattern = "yyyy-MM-dd HH:mm:ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withZone(ZoneId.of("America/Sao_Paulo"));
        final LocalDateTime dateTime = LocalDateTime.parse(strLocalDateTime, formatter);

        return dateTime;
    }
}
