package com.vertyce.nfe.pis;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import com.vertyce.util.Util;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorPISTest {
    @Spy
    private GeradorPIS geradorPIS;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarPIS(){
        final InfNFe infNFe = Util.getInfNFeComImposto();

        geradorPIS.gerarPIS(infNFe);

        PIS pis = Util.getPIS(infNFe, 0);
        assertThat(pis, notNullValue());
    }

    @Test
    public void naoDeveGerarPISSemDet(){
        final InfNFe infNFe = new InfNFe();

        geradorPIS.gerarPIS(infNFe);

        PIS pis = Util.getPIS(infNFe, 0);
        assertThat(pis, nullValue());
    }

    @Test
    public void naoDeveGerarPISSemImposto(){
        final InfNFe infNFe = new InfNFe();
        InfNFe.Det det = new InfNFe.Det();
        infNFe.getDet().add(det);

        geradorPIS.gerarPIS(infNFe);

        PIS pis = Util.getPIS(infNFe, 0);
        assertThat(pis, nullValue());
    }
}
