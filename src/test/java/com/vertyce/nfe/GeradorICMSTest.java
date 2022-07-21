package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import com.vertyce.util.Util;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.xml.bind.JAXBElement;
import java.nio.charset.CoderResult;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorICMSTest {

    @Spy
    private com.vertyce.nfe.GeradorICMS geradorICMS;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarICMS(){
        TNFe.InfNFe infNFe = Util.getInfNfeComImposto();

        geradorICMS.gerarICMS(infNFe);

        TNFe.InfNFe.Det.Imposto.ICMS icms = Util.getICMS(infNFe);
        assertThat(icms, notNullValue());
    }
}
