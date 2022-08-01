package com.vertyce.util;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
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
     * Adiciona um novo object COFINS em InfNFe -> Det -> Imposto -> List Content. O novo objeto será criado e adicionado
     * em todos os itens do Det.
     * @param infNFe objeto InfNFe.
     */
    public static void addCOFINS(InfNFe infNFe) {
        infNFe.getDet().stream()
                .map(det -> det.getImposto())
                .forEach(imposto -> imposto.getContent()
                        .add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(new COFINS())));
    }

    /**
     * Retorna objeto COFINS do imposto pelo índice do Det do objeto InfNFe. <br>
     * isola lista de Det's; <br>
     * filtra pelo objeto imposto diferente de nulo; <br>
     * retorna esse objeto Imposto; <br>
     * filtra pela lista de JAXBElement('imposto.Content') que diferem de vazia; <br>
     * retorna lista de elementos do Content; <br>
     * para cada elemento dessa lista: filtra-se pelos itens do tipo COFINS e retorna o primeiro objeto encontrado ou retorna nulo; <br>
     * converte o objeto retornado para COFINS ou então retorna-se nulo. <br>
     *
     * @param infNFe
     * @return objeto COFINS.
     */
    public static COFINS getCOFINS(InfNFe infNFe, int indexDet){
        COFINS cofins = null;

        final List<Det> dets = infNFe.getDet();
        if (dets.size() >= 1) {
            final Det det = dets.get(indexDet);
            final Det.Imposto imposto = det.getImposto();

            if (imposto != null) {
                cofins = det.getImposto().getContent().stream()
                        .filter(jaxb -> jaxb.getDeclaredType().equals(COFINS.class))
                        .map(jaxbElement -> jaxbElement.getValue())
                        .map(objectCofins -> (COFINS) objectCofins)
                        .findFirst()
                        .orElse(null);
            }
        }
        return cofins;
    }

    /**
     * Adiciona um novo object PIS em InfNFe -> Det -> Imposto -> List Content. O novo objeto será criado e adicionado
     * em todos os itens do Det.
     * @param infNFe objeto InfNFe.
     */
    public static void addPIS(InfNFe infNFe) {
        infNFe.getDet().stream()
                .map(det -> det.getImposto())
                .forEach(imposto -> imposto.getContent()
                        .add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(new PIS())));
    }

    /**
     * Retorna objeto PIS do imposto pelo índice do 'Det' do objeto InfNFe. <br>
     * isola lista de Det's; <br>
     * filtra pelo objeto imposto diferente de nulo; <br>
     * retorna esse objeto Imposto; <br>
     * filtra pela lista de JAXBElement('imposto.Content') que diferem de vazia; <br>
     * retorna lista de elementos do Content; <br>
     * para cada elemento dessa lista: filtra-se pelos itens do tipo PIS e retorna o primeiro objeto encontrado ou retorna nulo; <br>
     * converte o objeto retornado para PIS ou então retorna-se nulo. <br>
     *
     * @param infNFe
     * @return objeto PIS.
     */
    public static PIS getPIS(InfNFe infNFe, int indexDet){
        PIS pis = null;

        final List<Det> dets = infNFe.getDet();
        if (dets.size() >= 1) {
            final Det det = dets.get(indexDet);
            final Det.Imposto imposto = det.getImposto();

            if (imposto != null) {
                pis = det.getImposto().getContent().stream()
                        .filter(jaxb -> jaxb.getDeclaredType().equals(PIS.class))
                        .map(jaxbElement -> jaxbElement.getValue())
                        .map(objectPis -> (PIS) objectPis)
                        .findFirst()
                        .orElse(null);
            }
        }
        return pis;
    }

    /**
     * Adiciona novo ICMS em: InfNFe -> Det -> Imposto -> lista Content. Isso será feito para todos os 'Dets'.
     * @param infNFe
     */
    public static void addICMS(InfNFe infNFe){
        infNFe.getDet().stream()
                .map(det -> det.getImposto())
                .forEach(imposto -> imposto.getContent()
                        .add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(new ICMS())));
    }

    /**
     * Captura o 'item' da lista de 'Det' pelo índice, do parâmetro InfNFe; <br>
     * Captura o objeto imposto desse 'item'; <br>
     * Captura a lista do tipo JAXBElement desse Imposto; <br>
     * Dessa lista é retornado o valor do primeiro 'item' convertido em um objeto ICMS.
     * @param infNFe
     * @return objeto ICMS.
     */
    public static ICMS getICMS(InfNFe infNFe, int indexDet){
            ICMS icms = null;

            final List<Det> dets = infNFe.getDet();
            if (dets.size() >= 1) {
                final Det det = dets.get(indexDet);
                final Det.Imposto imposto = det.getImposto();

                if (imposto != null) {
                    icms = det.getImposto().getContent().stream()
                            .filter(jaxb -> jaxb.getDeclaredType().equals(ICMS.class))
                            .map(jaxbElement -> jaxbElement.getValue())
                            .map(objectICMS -> (ICMS) objectICMS)
                            .findFirst()
                            .orElse(null);
                }
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
