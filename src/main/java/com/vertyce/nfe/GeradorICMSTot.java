package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import com.vertyce.enums.EICMSTotMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class GeradorICMSTot implements IGeradorICMSTot {

    /**
     * Invoca um método pelo nome e retorna o resultado dessa invocação. <br>
     * - receberá o nome do método que será invocado;
     * - invocará o método;
     * - retornará o resultado como o tipo Object.
     *
     * @param prod      instância do método que será invocado.
     * @param nomeField nome do método da classe Prod.
     * @return retorno da invocação do método.
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object getValorMethdodInvoke(Prod prod, String nomeField) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        Object objectValue;

        final String nome = "get" + nomeField;
        final Method declaredMethod = Prod.class.getDeclaredMethod(nome);
        objectValue = declaredMethod.invoke(prod);
        return objectValue;
    }

    /**
     * Verifica se existe um método na classe Prod com o nome passado por parâmetro.
     *
     * @param nomeMethod nome do método.
     * @return true caso afirmativo, false caso contrário.
     */
    private boolean methodExiste(String nomeMethod) {
        boolean existeMethod = true;
        final String nome = "get" + nomeMethod;
        try {
            Prod.class.getDeclaredMethod(nome);
        } catch (NoSuchMethodException e) {
            existeMethod = false;
        }

        return existeMethod;
    }

    /**
     * Retorna o valor total de um campo(Field), com nome passado por parâmetro(nomeMethod), da classe Prod. <br>
     * O cáluclo do total será feito percorrendo a todos os itens Det -> prod -> 'nome do campo' e efetuando a invocação
     * do método 'det' deste campo. <br>
     * - se o método do Field não existir será retornado nulo;
     * - se o objeto Prod do Det for igual a nulo, será retornado '0.00';
     * - se um dos Exception esperados forem disparados, será retornado '0.00';
     *
     * @param dets       lista de Det.
     * @param nomeMethod nome do método que será invocado para retornar o valor do Field de Prod.
     * @return valor total do campo.
     * @see Prod
     */
    protected String getTotalPorCampo(List<Det> dets, String nomeMethod) {

        final BigDecimal bgZero = new BigDecimal("0.00");
        BigDecimal total;
        String strTotal = "0.00";

        boolean methodExiste = methodExiste(nomeMethod);

        if (methodExiste) {
            if (!dets.isEmpty()) {
                total = dets.stream()
                        .filter(det -> det.getProd() != null)
                        .map(det -> det.getProd())
                        .map(prod -> {
                            Object valor;
                            BigDecimal bgValor = bgZero;

                            try {
                                valor = getValorMethdodInvoke(prod, nomeMethod);
                                bgValor = new BigDecimal(String.valueOf(valor));

                            } catch (NumberFormatException nfe) {
                                System.err.println(nfe.getMessage());
                            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                System.err.println(e.getMessage());
                            }

                            return bgValor;

                        })
                        .reduce(BigDecimal::add)
                        .orElse(bgZero);

                strTotal = String.valueOf(total);
            }
        } else {
            strTotal = null;
        }

        return strTotal;
    }

    /**
     * Calcula o total do 'vPIS' de todas as tributações. <br>
     * Percorre a lista de: InfNFe -> Det -> Imposto -> lista de Content; <br>
     * Verifica se o elemento da lista de Content(JAXBElement) é do tipo PIS. <br>
     * Filtra resultados diferentes de nulo; <br>
     * Converte o resultado encontrado para PIS; <br>
     * Converte e retorna o valor da tributação 'PISAliq' do resultado encontrado para BigDecimal; <br>
     * Coma o valor encontrado e retorna o total. <br>
     * @param dets lista de 'Det'.
     * @return retorna o total de 'vPIS'.
     */
    protected String getTotalVPIS(List<Det> dets) {
        final BigDecimal totalVPis = DetUtil.getStreamDetImpostoContent(dets.stream())
                .map(jaxbElements -> {
                    Object objectPIS = jaxbElements.stream()
                            .filter(jaxb -> jaxb.getDeclaredType().equals(PIS.class))
                            .map(jaxb -> jaxb.getValue())
                            .findFirst().orElse(null);
                    return objectPIS;
                })

                .filter(Objects::nonNull)
                .map(valuePis -> {
                    final PIS pis = (PIS) valuePis;
                    return pis;

                })

                .filter(pis -> pis.getPISAliq() != null)
                .map(pis -> new BigDecimal(pis.getPISAliq().getVPIS()))
                .reduce(BigDecimal::add)
                .orElse(null);

        return String.valueOf(totalVPis == null ? "0.00" : totalVPis);
    }

    /**
     * Calcula o total do 'vCOFINS' de todas as tributações. <br>
     * Percorre a lista de: InfNFe -> Det -> Imposto -> lista de Content; <br>
     * Verifica se o elemento da lista de Content(JAXBElement) é do tipo COFINS. <br>
     * Filtra resultados diferentes de nulo; <br>
     * Converte o resultado encontrado para COFINS; <br>
     * Converte e retorna o valor da tributação 'COFINSAliq' do resultado encontrado para BigDecimal; <br>
     * Coma o valor encontrado e retorna o total. <br>
     * @param dets lista de 'Det'.
     * @return retorna o total de 'vCOFINS'.
     */
    private String getTotalVCOFINS(List<Det> dets) {
        final BigDecimal totalVCofins = DetUtil.getStreamDetImpostoContent(dets.stream())
                .map(jaxbElements -> {
                    Object objectCOFINS = jaxbElements.stream()
                            .filter(jaxb -> jaxb.getDeclaredType().equals(COFINS.class))
                            .map(jaxb -> jaxb.getValue())
                            .findFirst().orElse(null);
                    return objectCOFINS;
                })

                .filter(Objects::nonNull)
                .map(valueCofins -> {
                    final COFINS cofins = (COFINS) valueCofins;
                    return cofins;

                })

                .map(cofins -> new BigDecimal(cofins.getCOFINSAliq().getVCOFINS()))
                .reduce(BigDecimal::add)
                .orElse(null);

        return String.valueOf(totalVCofins == null ? "0.00" : totalVCofins);
    }

    /**
     * Cria e atribuí na InfNFe um objeto ICMSTot e atribuí os seus campos com os seus respectivos totais.
     *
     * @param infNFe objeto InfNFe.
     */
    @Override
    public void gerarICMSTot(InfNFe infNFe) {

        if (infNFe.getTotal() != null) {

            final ICMSTot icmsTot = new ICMSTot();
            infNFe.getTotal().setICMSTot(icmsTot);

            final String vBC = "0.00";
            final String vICMS = "0.00";
            final String vICMSDeson = "0.00";
            final String vBCST = "0.00";
            final String vST = "0.00";
            final String vProd = getTotalPorCampo(infNFe.getDet(), EICMSTotMethod.VPROD.getNomeMethod());
            final String vFrete = getTotalPorCampo(infNFe.getDet(), EICMSTotMethod.VFRETE.getNomeMethod());
            final String vSeg = getTotalPorCampo(infNFe.getDet(), EICMSTotMethod.VSEG.getNomeMethod());
            final String vDesc = getTotalPorCampo(infNFe.getDet(), EICMSTotMethod.VDESC.getNomeMethod());
            final String vII = "0.00";
            final String vIPI = "0.00";
            final String vPIS = getTotalVPIS(infNFe.getDet());
            final String vCOFINS = getTotalVCOFINS(infNFe.getDet());
            final String vOutro = getTotalPorCampo(infNFe.getDet(), EICMSTotMethod.VOUTRO.getNomeMethod());

            final BigDecimal bgVICMSDeson = new BigDecimal(vICMSDeson);
            final BigDecimal bgVST = new BigDecimal(vST);
            final BigDecimal bgVProd = new BigDecimal(vProd);
            final BigDecimal bgVFrete = new BigDecimal(vFrete);
            final BigDecimal bgVSeg = new BigDecimal(vSeg);
            final BigDecimal bgVDesc = new BigDecimal(vDesc);
            final BigDecimal bgVII = new BigDecimal(vII);
            final BigDecimal bgVIPI = new BigDecimal(vIPI);
            final BigDecimal bgVOutro = new BigDecimal(vOutro);

            final String vNF = String.valueOf(bgVST.add(bgVProd).add(bgVFrete).add(bgVSeg).add(bgVII).add(bgVIPI).add(bgVOutro)
                    .subtract(bgVICMSDeson).subtract(bgVDesc));

            icmsTot.setVBC(vBC);
            icmsTot.setVICMS(vICMS);
            icmsTot.setVICMSDeson(vICMSDeson);
            icmsTot.setVBCST(vBCST);
            icmsTot.setVST(vST);
            icmsTot.setVProd(vProd);
            icmsTot.setVFrete(vFrete);
            icmsTot.setVSeg(vSeg);
            icmsTot.setVDesc(vDesc);
            icmsTot.setVII(vII);
            icmsTot.setVIPI(vIPI);
            icmsTot.setVPIS(vPIS);
            icmsTot.setVCOFINS(vCOFINS);
            icmsTot.setVOutro(vOutro);
            icmsTot.setVNF(vNF);
        }
    }
}
