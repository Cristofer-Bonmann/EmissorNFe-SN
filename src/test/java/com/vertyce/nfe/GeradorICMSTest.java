package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import com.vertyce.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
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
        InfNFe infNFe = Util.getInfNFeComImposto();

        geradorICMS.gerarICMS(infNFe);

        Det.Imposto.ICMS icms = Util.getICMS(infNFe, 0);
        assertThat(icms, notNullValue());
    }

    @Test
    public void naoDeveGerarSemImposto(){
        final InfNFe infNFe = new InfNFe();
        final Det det = new Det();

        infNFe.getDet().add(det);

        geradorICMS.gerarICMS(infNFe);

        assertThat(infNFe.getDet().get(0).getImposto(), nullValue());
    }
}
