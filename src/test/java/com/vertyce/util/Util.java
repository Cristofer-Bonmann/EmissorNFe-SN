package com.vertyce.util;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

import javax.xml.bind.JAXBElement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Util {

    /**
     * Captura o primeiro item da lista de Det's do parâmetro InfNFe; <br>
     * Captura o objeto imposto desse item; <br>
     * Captura a lista do tipo JAXBElement desse Imposto; <br>
     * Dessa lista é retornado o valor do primeiro item convertido em um objeto ICMS.
     * @param infNFe
     * @return objeto ICMS.
     */
    public static TNFe.InfNFe.Det.Imposto.ICMS getICMS(TNFe.InfNFe infNFe){
        List<JAXBElement<?>> jaxeICMS = infNFe.getDet().get(0).getImposto().getContent();
        return (TNFe.InfNFe.Det.Imposto.ICMS) jaxeICMS.get(0).getValue();
    }

    /**
     * Cria um novo objeto InfNFe, cria um novo objeto Det e inseri ele objeto na lista de det's do InfNFe.
     * No Det criado, será adicionado um novo objeto Imposto.
     * @return novo InfNFe.
     */
    public static TNFe.InfNFe getInfNFeComImposto(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
        det.setImposto(new TNFe.InfNFe.Det.Imposto());
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
