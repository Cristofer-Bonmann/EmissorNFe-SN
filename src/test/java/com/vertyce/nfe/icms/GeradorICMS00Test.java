package com.vertyce.nfe.icms;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import com.vertyce.util.Util;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorICMS00Test {

    @Spy
    private GeradorICMS00 geradorICMS00;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarICMS00ComVICMS(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        Det.Imposto.ICMS icms = Util.getICMS(infNFe, 0);
        Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00.getVICMS(), is("90.00"));
    }

    @Test
    public void deveGerarICMS00ComPICMS(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        Det.Imposto.ICMS icms = Util.getICMS(infNFe, 0);
        Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00.getPICMS(), is("10.0000"));
    }

    @Test
    public void deveGerarICMS00ComVBC(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        Det.Imposto.ICMS icms = Util.getICMS(infNFe, 0);
        Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00.getVBC(), is("100.00"));
    }

    @Test
    public void deveGerarICMS00ComModBC(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        Det.Imposto.ICMS icms = Util.getICMS(infNFe, 0);
        Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00.getModBC(), is("3"));
    }

    @Test
    public void deveGerarICMS00ComCST(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        Det.Imposto.ICMS icms = Util.getICMS(infNFe, 0);
        Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00.getCST(), is("00"));
    }

    @Test
    public void deveGerarICMS00ComOrig(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        Det.Imposto.ICMS icms = Util.getICMS(infNFe, 0);
        Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00.getOrig(), is("0"));
    }

    @Test
    public void deveGerarICMS00(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        Det.Imposto.ICMS icms = Util.getICMS(infNFe, 0);
        Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00, notNullValue());
    }

    @Test
    public void naoDeveGerarICMS00SemContentICMS(){
        final InfNFe infNFe = new InfNFe();

        final Det.Imposto imposto = new Det.Imposto();
        JAXBElement<String> stringJAXBElement = new JAXBElement<>(new QName("", ""), String.class, "Não é ICMS");
        imposto.getContent().add(stringJAXBElement);

        final Det det = new Det();
        det.setImposto(imposto);

        infNFe.getDet().add(det);

        geradorICMS00.geraICMS00(infNFe);

        JAXBElement<?> jaxbElement = infNFe.getDet().get(0).getImposto().getContent().get(0);
        boolean ehICMS = jaxbElement.getValue() instanceof Det.Imposto.ICMS;

        assertThat(ehICMS, is(false));
    }

    @Test
    public void naoDeveGerarICMS00SemICMS(){
        InfNFe infNFe = Util.getInfNFeComImposto();

        geradorICMS00.geraICMS00(infNFe);

        Det.Imposto.ICMS icms = Util.getICMS(infNFe, 0);
        assertThat(icms, nullValue());
    }

    @Test
    public void deveGerarICMS00SemImposto(){
        final InfNFe infNFe = new InfNFe();
        infNFe.getDet().add(new Det());

        geradorICMS00.geraICMS00(infNFe);

        assertThat(infNFe.getDet().get(0).getImposto(), nullValue());
    }
}
