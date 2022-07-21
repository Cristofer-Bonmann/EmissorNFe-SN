package com.vertyce.nfe.icms;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import com.vertyce.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorICMS00Test {

    @Spy
    private GeradorICMS00 geradorICMS00;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarICMS00(){
        final TNFe.InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        TNFe.InfNFe.Det.Imposto.ICMS icms = Util.getICMS(infNFe);
        TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00, notNullValue());
    }
}
