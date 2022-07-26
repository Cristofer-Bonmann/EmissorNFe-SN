package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorTotalTest {

    @Spy
    private GeradorTotal geradorTotal;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarTotalNFe(){
        final InfNFe infNFe = new InfNFe();

        geradorTotal.gerarTotal(infNFe);

        assertThat(infNFe.getTotal(), notNullValue());
    }
}
