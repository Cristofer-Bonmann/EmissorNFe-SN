package com.vertyce.util;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.io.File.createTempFile;

public class Util {

    /**
     * Adiciona um novo objeto PIS na Content PIS, do objeto Imposto, no primeiro item Det do parâmetro InfNFe.
     * @param infNFe
     */
    public static void addPIS(InfNFe infNFe) {
        infNFe.getDet().get(0)
                .getImposto().getContent()
                .add(new ObjectFactory()
                        .createTNFeInfNFeDetImpostoPIS(new PIS()));
    }

    /**
     * Retorna objeto PIS do imposto do primeiro Det do objeto InfNFe. <br>
     * isola lista de Det's; <br>
     * filtra pelo objeto imposto diferente de nulo; <br>
     * retorna esse objeto Imposto; <br>
     * filtra pela lista de JAXBElement(imposto.Content) que seja diferente de vazia; <br>
     * retorna lista de elementos do Content; <br>
     * para cada elemento dessa lista: filtra-se pelos itens do tipo PIS e retorna o primeiro objeto encontrado ou retorna nulo; <br>
     * converte o objeto retornado para PIS ou então retorna-se nulo. <br>
     *
     * @param infNFe
     * @return objeto PIS.
     */
    public static PIS getPIS(InfNFe infNFe){
        final Det det = infNFe.getDet().get(0);

        PIS pis = det.getImposto().getContent().stream()
                .filter(jaxb -> jaxb.getDeclaredType().equals(PIS.class))
                .map(jaxbElement -> jaxbElement.getValue())
                .map(objectPis -> (PIS) objectPis)
                .findFirst()
                .orElse(null);
        return pis;
    }

    /**
     * Adiciona um novo objeto ICMS na Content ICMS, do objeto Imposto, do primeiro item Det do parâmetro InfNFe.
     * @param infNFe
     */
    public static void addICMS(InfNFe infNFe){
        infNFe.getDet().get(0)
                .getImposto().getContent()
                .add(new ObjectFactory()
                        .createTNFeInfNFeDetImpostoICMS(new ICMS()));
    }

    /**
     * Captura o primeiro item da lista de Det's do parâmetro InfNFe; <br>
     * Captura o objeto imposto desse item; <br>
     * Captura a lista do tipo JAXBElement desse Imposto; <br>
     * Dessa lista é retornado o valor do primeiro item convertido em um objeto ICMS.
     * @param infNFe
     * @return objeto ICMS.
     */
    public static ICMS getICMS(InfNFe infNFe){
        ICMS icms = null;
        List<JAXBElement<?>> content = infNFe.getDet().get(0).getImposto().getContent();

        if (content != null && content.size() >= 1) {
            List<JAXBElement<?>> jaxeICMS = infNFe.getDet().get(0).getImposto().getContent();
            icms = (ICMS) jaxeICMS.get(0).getValue();
        }
        return icms;
    }

    /**
     * Cria um novo objeto InfNFe, cria um novo objeto Det e inseri ele objeto na lista de det's do InfNFe.
     * No Det criado, será adicionado um novo objeto Imposto.
     * @return novo InfNFe.
     */
    public static InfNFe getInfNFeComImposto(){
        final InfNFe infNFe = new InfNFe();
        Det det = new Det();
        det.setImposto(new Det.Imposto());
        infNFe.getDet().add(det);

        return infNFe;
    }

    // TODO: 21/07/2022 inserir doc
    public static File criaEscreveArquivoTemp(String prefixo, String sufixo, String conteudo){
        File tempFile;
        try {
            tempFile = createTempFile(prefixo, sufixo);

            try {
                FileWriter myWriter = new FileWriter(tempFile.getAbsolutePath());
                myWriter.write(conteudo);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return tempFile;
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
