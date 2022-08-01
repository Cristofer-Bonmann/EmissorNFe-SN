package com.vertyce.nfe.pis;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import com.vertyce.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorPISNTTest {

    @Spy
    private GeradorPISNT geradorPISNT;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarPISNTComCST(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addPIS(infNFe);

        geradorPISNT.gerarPISNT(infNFe);

        final PIS.PISNT pisnt = Util.getPIS(infNFe, 0).getPISNT();
        assertThat(pisnt.getCST(), is("04"));
    }

    @Test
    public void deveGerarPISNT(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);
        Util.addPIS(infNFe);

        geradorPISNT.gerarPISNT(infNFe);

        final PIS pis = Util.getPIS(infNFe, 0);
        assertThat(pis.getPISNT(), notNullValue());
    }
}
