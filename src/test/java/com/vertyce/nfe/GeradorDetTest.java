package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorDetTest {

    @Spy
    private GeradorDet geradorDet;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComNItem(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDet.gerarDet(infNFe);

        List<TNFe.InfNFe.Det> det = infNFe.getDet();
        assertThat(det.get(0).getNItem(), CoreMatchers.is("1"));
    }

    @Test
    public void deveGerarDet(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDet.gerarDet(infNFe);

        assertThat(infNFe.getDet(), notNullValue());
    }
}
