package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorInfNFeTest {

    @Spy
    private GeradorInfNfe geradorInfNfe;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComVersao(){
        TNFe.InfNFe infNFe = geradorInfNfe.gerarInfNFe();

        assertThat(infNFe.getVersao(), is("4.00"));
    }

    @Test
    public void deveGerarComId(){
        TNFe.InfNFe infNFe = geradorInfNfe.gerarInfNFe();

        assertThat(infNFe.getId(), is("NFe00000000000000000000000000000000000000000000"));
    }
}
