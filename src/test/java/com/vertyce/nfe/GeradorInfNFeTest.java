package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import org.apache.woden.wsdl20.validation.Assertion;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorInfNFeTest {

    @Spy
    private GeradorInfNfe presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComVersao(){
        TNFe.InfNFe infNFe = presenter.gerarInfNFe();

        assertThat(infNFe.getVersao(), is("4.00"));
    }

    @Test
    public void deveGerarComId(){
        TNFe.InfNFe infNFe = presenter.gerarInfNFe();

        assertThat(infNFe.getId(), is("NFe00000000000000000000000000000000000000000000"));
    }
}
