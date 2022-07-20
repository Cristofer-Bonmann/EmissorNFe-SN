package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GeradorDestTest {

    @Spy
    @InjectMocks
    private GeradorDest geradorDest;

    @Mock
    private DestinatarioView view;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComIE(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TNFe.InfNFe.Dest emit = infNFe.getDest();
        assertThat(emit.getIE(), is("248166980"));
    }

    @Test
    public void deveGerarComCEP(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TEndereco enderDest = infNFe.getDest().getEnderDest();
        assertThat(enderDest.getCEP(), is("57490000"));
    }

    @Test
    public void deveGerarComUFNulo(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        final String siglaUf = null;

        when(view.getSiglaUf()).thenReturn(siglaUf);
        geradorDest.gerarDest(infNFe);

        verify(view).getSiglaUf();

        TEndereco enderDest = infNFe.getDest().getEnderDest();
        assertThat(enderDest.getUF(), nullValue());
    }

    @Test
    public void deveGerarComUF(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        final String siglaUf = "AL";

        when(view.getSiglaUf()).thenReturn(siglaUf);
        geradorDest.gerarDest(infNFe);

        verify(view).getSiglaUf();

        TEndereco enderDest = infNFe.getDest().getEnderDest();
        assertThat(enderDest.getUF(), is(TUf.AL));
    }

    @Test
    public void deveGerarComxMun(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TEndereco enderDest = infNFe.getDest().getEnderDest();
        assertThat(enderDest.getXMun(), is("Água Branca"));
    }

    @Test
    public void deveGerarComCMun(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TEndereco enderDest = infNFe.getDest().getEnderDest();
        assertThat(enderDest.getCMun(), is("2700102"));
    }

    @Test
    public void deveGerarComxBairro(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TEndereco enderDest = infNFe.getDest().getEnderDest();
        assertThat(enderDest.getXBairro(), is("BAIRRO DE TESTE"));
    }

    @Test
    public void deveGerarComXCpl(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TEndereco enderDest = infNFe.getDest().getEnderDest();
        assertThat(enderDest.getXCpl(), is("SEM COMPLEMENTO"));
    }

    @Test
    public void deveGerarComNro(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TEndereco enderDest = infNFe.getDest().getEnderDest();
        assertThat(enderDest.getNro(), is("999"));
    }

    @Test
    public void deveGerarComXlgr(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TEndereco enderDest = infNFe.getDest().getEnderDest();
        assertThat(enderDest.getXLgr(), is("LOGRADOURO DE TESTE"));
    }

    @Test
    public void deveGerarComXNome(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TNFe.InfNFe.Dest dest = infNFe.getDest();
        assertThat(dest.getXNome(), is("TESTE-HOMOLOGAÇÂO"));
    }

    @Test
    public void deveGerarComCNPJ(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        TNFe.InfNFe.Dest dest = infNFe.getDest();
        assertThat(dest.getCNPJ(), is("92638680000191"));
    }

    @Test
    public void deveGerarDest(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorDest.gerarDest(infNFe);

        assertThat(infNFe.getDest(), notNullValue());
    }
}
