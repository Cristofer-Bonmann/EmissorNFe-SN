package com.vertyce.builders;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101;

import javax.xml.bind.JAXBElement;

/**
 * Builder do objeto InfNFe com tributação ICMSSN 101 com apenas um item como produto.
 */
public class ICMSSN101Builder {

    private InfNFe infNFe;
    private ICMSSN101Builder(){}

    public static ICMSSN101Builder getICMSSN101(){
        final ICMSSN101Builder icmssn101Builder = new ICMSSN101Builder();

        final ICMS icms = new ICMS();

        final ObjectFactory of = new ObjectFactory();
        final InfNFe infNFe = of.createTNFeInfNFe();
        final Det det = of.createTNFeInfNFeDet();
        final Imposto imposto = of.createTNFeInfNFeDetImposto();
        final ICMSSN101 icmssn101 = of.createTNFeInfNFeDetImpostoICMSICMSSN101();
        final JAXBElement<ICMS> jaxbElementICMS = of.createTNFeInfNFeDetImpostoICMS(icms);

        icms.setICMSSN101(icmssn101);
        imposto.getContent().add(jaxbElementICMS);
        det.setImposto(imposto);
        infNFe.getDet().add(det);

        icmssn101Builder.infNFe = infNFe;

        return icmssn101Builder;
    }

    public InfNFe get(){
        return this.infNFe;
    }
}
