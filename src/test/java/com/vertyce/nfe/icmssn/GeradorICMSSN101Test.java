package com.vertyce.nfe.icmssn;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import com.vertyce.util.Util;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorICMSSN101Test {

    @Spy
    private GeradorICMSSN101 geradorICMSSN101;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarICMSSN101ComVCredICMSSN(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMSSN101.gerarICMSSN101(infNFe);

        ICMS.ICMSSN101 icmssn101 = Util.getICMS(infNFe).getICMSSN101();
        assertThat(icmssn101.getVCredICMSSN(), is("10.00"));
    }

    @Test
    public void deveGerarICMSSN101ComPCredSN(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMSSN101.gerarICMSSN101(infNFe);

        ICMS.ICMSSN101 icmssn101 = Util.getICMS(infNFe).getICMSSN101();
        assertThat(icmssn101.getPCredSN(), is("10.0000"));
    }

    @Test
    public void deveGerarICMSSN101ComCSOSN(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMSSN101.gerarICMSSN101(infNFe);

        ICMS.ICMSSN101 icmssn101 = Util.getICMS(infNFe).getICMSSN101();
        assertThat(icmssn101.getCSOSN(), is("101"));
    }

    @Test
    public void deveGerarICMSSN101ComOrig(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMSSN101.gerarICMSSN101(infNFe);

        ICMS.ICMSSN101 icmssn101 = Util.getICMS(infNFe).getICMSSN101();
        assertThat(icmssn101.getOrig(), is("0"));
    }

    @Test
    public void deveGerarICMSSN101(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMSSN101.gerarICMSSN101(infNFe);

        final ICMS icms = Util.getICMS(infNFe);
        assertThat(icms.getICMSSN101(), notNullValue());
    }

    @Test
    public void naoDeveGerarICMSSN101SemContentICMS(){
        final InfNFe infNFe = new InfNFe();

        final InfNFe.Det.Imposto imposto = new InfNFe.Det.Imposto();
        JAXBElement<String> stringJAXBElement = new JAXBElement<>(new QName("", ""), String.class, "Não é ICMS");
        imposto.getContent().add(stringJAXBElement);

        final InfNFe.Det det = new InfNFe.Det();
        det.setImposto(imposto);

        infNFe.getDet().add(det);

        geradorICMSSN101.gerarICMSSN101(infNFe);

        JAXBElement<?> jaxbElement = infNFe.getDet().get(0).getImposto().getContent().get(0);
        boolean ehICMS = jaxbElement.getValue() instanceof InfNFe.Det.Imposto.ICMS;

        assertThat(ehICMS, is(false));
    }

    @Test
    public void naoDeveGerarICMSSN101SemICMS(){
        InfNFe infNFe = Util.getInfNFeComImposto();

        geradorICMSSN101.gerarICMSSN101(infNFe);

        InfNFe.Det.Imposto.ICMS icms = Util.getICMS(infNFe);
        assertThat(icms, nullValue());
    }

    @Test
    public void deveGerarICMSSN101SemImposto(){
        final InfNFe infNFe = new InfNFe();
        infNFe.getDet().add(new InfNFe.Det());

        geradorICMSSN101.gerarICMSSN101(infNFe);

        assertThat(infNFe.getDet().get(0).getImposto(), nullValue());
    }
}
