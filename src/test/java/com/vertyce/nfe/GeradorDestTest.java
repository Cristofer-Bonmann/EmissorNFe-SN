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

public class GeradorDestTest {

    @Spy
    private GeradorDest geradorDest;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarDest(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        assertThat(infNFe.getDest(), notNullValue());
    }
}
