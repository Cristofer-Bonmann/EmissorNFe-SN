package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorInfAdProdTest {

    @Spy
    private GeradorInfAdProd geradorInfAdProd;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarInfAdProd(){
        final InfNFe infNFe = new InfNFe();
        infNFe.getDet().add(new InfNFe.Det());

        geradorInfAdProd.gerarInfAdProd(infNFe);

        final String infAdProd = infNFe.getDet().get(0).getInfAdProd();
        assertThat(infAdProd, is("INFORMAÇÃO ADICIONAL - HOMOLOGAÇÃO"));
    }
}
