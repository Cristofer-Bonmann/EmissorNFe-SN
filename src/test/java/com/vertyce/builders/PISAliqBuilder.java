package com.vertyce.builders;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;

import javax.xml.bind.JAXBElement;

/**
 * Builder de uma InfNFe -> Det -> Imposto -> PIS -> PISAliq.
 */
public class PISAliqBuilder {

    private InfNFe infNFe;

    private PISAliqBuilder(){}

    public static PISAliqBuilder getPISAliq(){
        final PISAliqBuilder pisAliqBuilder = new PISAliqBuilder();

        final ObjectFactory of = new ObjectFactory();
        final InfNFe infNFe = of.createTNFeInfNFe();
        final InfNFe.Det det = of.createTNFeInfNFeDet();
        final InfNFe.Det.Imposto imposto = of.createTNFeInfNFeDetImposto();
        final InfNFe.Det.Imposto.PIS pis = of.createTNFeInfNFeDetImpostoPIS();
        final InfNFe.Det.Imposto.PIS.PISAliq pisAliq = of.createTNFeInfNFeDetImpostoPISPISAliq();
        final JAXBElement<InfNFe.Det.Imposto.PIS> jaxbElementPis = of.createTNFeInfNFeDetImpostoPIS(pis);

        infNFe.getDet().add(det);
        det.setImposto(imposto);
        imposto.getContent().add(jaxbElementPis);
        pis.setPISAliq(pisAliq);

        pisAliq.setVPIS("0.50");

        pisAliqBuilder.infNFe = infNFe;

        return pisAliqBuilder;
    }

    public InfNFe get(){
        return this.infNFe;
    }
}
