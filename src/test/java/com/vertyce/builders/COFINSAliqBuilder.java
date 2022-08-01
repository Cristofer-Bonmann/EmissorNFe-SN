package com.vertyce.builders;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;

import javax.xml.bind.JAXBElement;

/**
 * Builder de uma InfNFe -> Det -> Imposto -> COFINS -> COFINSAliq.
 */
public class COFINSAliqBuilder {

    private InfNFe infNFe;

    private COFINSAliqBuilder(){}

    public static COFINSAliqBuilder getCOFINSAliq(){
        final COFINSAliqBuilder cofinsAliqBuilder = new COFINSAliqBuilder();

        final ObjectFactory of = new ObjectFactory();
        final InfNFe infNFe = of.createTNFeInfNFe();
        final InfNFe.Det det = of.createTNFeInfNFeDet();
        final InfNFe.Det.Imposto imposto = of.createTNFeInfNFeDetImposto();
        final InfNFe.Det.Imposto.COFINS cofins = of.createTNFeInfNFeDetImpostoCOFINS();
        final InfNFe.Det.Imposto.COFINS.COFINSAliq cofinsAliq = of.createTNFeInfNFeDetImpostoCOFINSCOFINSAliq();
        final JAXBElement<InfNFe.Det.Imposto.COFINS> jaxbElementCofins = of.createTNFeInfNFeDetImpostoCOFINS(cofins);

        infNFe.getDet().add(det);
        det.setImposto(imposto);
        imposto.getContent().add(jaxbElementCofins);
        cofins.setCOFINSAliq(cofinsAliq);

        cofinsAliq.setVCOFINS("0.50");

        cofinsAliqBuilder.infNFe = infNFe;

        return cofinsAliqBuilder;
    }

    public InfNFe get(){
        return this.infNFe;
    }
}
