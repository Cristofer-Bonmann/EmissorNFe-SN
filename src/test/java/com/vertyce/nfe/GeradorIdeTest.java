package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorIdeTest {

    @Spy
    private GeradorIde geradorIde;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComNumeroNF(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getNNF(), is("1"));
    }

    @Test
    public void deveGerarComSerie(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getSerie(), is("1"));
    }

    @Test
    public void deveGerarComMod(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getMod(), is("55"));
    }

    @Test
    public void deveGerarComNatOp(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getNatOp(), is("VENDA DE MERCADORIA PRODUZIDA OU ADQUIRIDA POR TERCEIROS"));
    }

    @Test
    public void deveGerarComCNF(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getCNF(), is("00000001"));
    }

    @Test
    public void deveGerarComCUF(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getCUF(), is("27"));
    }

    @Test
    public void deveGerarIde(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        assertThat(infNFe.getIde(), notNullValue());
    }
}
