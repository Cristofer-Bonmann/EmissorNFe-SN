package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;
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
    public void deveGerarComUCom(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.getDet().add(new TNFe.InfNFe.Det());

        geradorProd.gerarProd(infNFe);

        TNFe.InfNFe.Det.Prod prod = infNFe.getDet().get(0).getProd();
        assertThat(prod.getUCom(), is("UNID"));
    }


    @Test
    public void deveGerarComCFOP(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.getDet().add(new TNFe.InfNFe.Det());

        geradorProd.gerarProd(infNFe);

        TNFe.InfNFe.Det.Prod prod = infNFe.getDet().get(0).getProd();
        assertThat(prod.getCFOP(), is("5102"));
    }

    @Test
    public void deveGerarComNCM(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.getDet().add(new TNFe.InfNFe.Det());

        geradorProd.gerarProd(infNFe);

        TNFe.InfNFe.Det.Prod prod = infNFe.getDet().get(0).getProd();
        assertThat(prod.getNCM(), is("29313997"));
    }

    @Test
    public void deveGerarComXProd(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.getDet().add(new TNFe.InfNFe.Det());

        geradorProd.gerarProd(infNFe);

        TNFe.InfNFe.Det.Prod prod = infNFe.getDet().get(0).getProd();
        assertThat(prod.getXProd(), is("PRODUTO PARA TESTE - HOMOLOGAÇÃO"));
    }

    @Test
    public void deveGerarComCEAN(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.getDet().add(new TNFe.InfNFe.Det());

        geradorProd.gerarProd(infNFe);

        TNFe.InfNFe.Det.Prod prod = infNFe.getDet().get(0).getProd();
        assertThat(prod.getCEAN(), is("1234567891234"));
    }

    @Test
    public void deveGerarComCProd(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.getDet().add(new TNFe.InfNFe.Det());

        geradorProd.gerarProd(infNFe);

        TNFe.InfNFe.Det.Prod prod = infNFe.getDet().get(0).getProd();
        assertThat(prod.getCProd(), is("1"));
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
