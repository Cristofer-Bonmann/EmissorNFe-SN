package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorProdTest {

    @Spy
    private GeradorProd geradorProd;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarProd(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.getDet().add(new TNFe.InfNFe.Det());

        geradorProd.gerarProd(infNFe);

        TNFe.InfNFe.Det.Prod prod = infNFe.getDet().get(0).getProd();
        assertThat(prod, notNullValue());
    }
}
