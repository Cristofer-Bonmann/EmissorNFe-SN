package com.vertyce.nfe.icms;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import com.vertyce.util.Util;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;
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
    public void deveGerarICMS00ComCST(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        InfNFe.Det.Imposto.ICMS icms = Util.getICMS(infNFe);
        InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00.getCST(), is("00"));
    }

    @Test
    public void deveGerarICMS00ComOrig(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        InfNFe.Det.Imposto.ICMS icms = Util.getICMS(infNFe);
        InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00.getOrig(), is("0"));
    }

    @Test
    public void deveGerarICMS00(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);

        geradorICMS00.geraICMS00(infNFe);

        InfNFe.Det.Imposto.ICMS icms = Util.getICMS(infNFe);
        InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = icms.getICMS00();
        assertThat(icms00, notNullValue());
    }
}
