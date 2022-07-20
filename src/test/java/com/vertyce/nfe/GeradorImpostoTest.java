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

public class GeradorImpostoTest {

    @Spy
    private GeradorImposto geradorImposto;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarImposto(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.getDet().add(new TNFe.InfNFe.Det());

        geradorImposto.gerarImposto(infNFe);

        TNFe.InfNFe.Det.Imposto imposto = infNFe.getDet().get(0).getImposto();
        assertThat(imposto, notNullValue());
    }
}
