package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnderEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUfEmi;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GeradorEmitTest {

    @Spy
    @InjectMocks
    private GeradorEmit geradorEmit;

    @Mock
    private EmitenteView view;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComIE(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        TNFe.InfNFe.Emit emit = infNFe.getEmit();
        assertThat(emit.getIE(), is("248166980"));
    }

    @Test
    public void deveGerarComCEP(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        TEnderEmi enderEmit = infNFe.getEmit().getEnderEmit();
        assertThat(enderEmit.getCEP(), is("57490000"));
    }

    @Test
    public void deveGerarComUFNulo(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        final String siglaUf = null;

        when(view.getSiglaUf()).thenReturn(siglaUf);
        geradorEmit.gerarEmit(infNFe);

        verify(view).getSiglaUf();

        TEnderEmi enderEmit = infNFe.getEmit().getEnderEmit();
        assertThat(enderEmit.getUF(), nullValue());
    }

    @Test
    public void deveGerarComUF(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        final String siglaUf = "AL";

        when(view.getSiglaUf()).thenReturn(siglaUf);
        geradorEmit.gerarEmit(infNFe);

        verify(view).getSiglaUf();

        TEnderEmi enderEmit = infNFe.getEmit().getEnderEmit();
        assertThat(enderEmit.getUF(), is(TUfEmi.AL));
    }

    @Test
    public void deveGerarComxMun(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        TEnderEmi enderEmit = infNFe.getEmit().getEnderEmit();
        assertThat(enderEmit.getXMun(), is("Água Branca"));
    }

    @Test
    public void deveGerarComCMun(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        TEnderEmi enderEmit = infNFe.getEmit().getEnderEmit();
        assertThat(enderEmit.getCMun(), is("2700102"));
    }

    @Test
    public void deveGerarComxBairro(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        TEnderEmi enderEmit = infNFe.getEmit().getEnderEmit();
        assertThat(enderEmit.getXBairro(), is("BAIRRO DE TESTE"));
    }

    @Test
    public void deveGerarComXCpl(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        TEnderEmi enderEmit = infNFe.getEmit().getEnderEmit();
        assertThat(enderEmit.getXCpl(), is("SEM COMPLEMENTO"));
    }

    @Test
    public void deveGerarComNro(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        TEnderEmi enderEmit = infNFe.getEmit().getEnderEmit();
        assertThat(enderEmit.getNro(), is("999"));
    }

    @Test
    public void deveGerarComXlgr(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        TEnderEmi enderEmit = infNFe.getEmit().getEnderEmit();
        assertThat(enderEmit.getXLgr(), is("LOGRADOURO DE TESTE"));
    }

    @Test
    public void deveGerarComXNome(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        TNFe.InfNFe.Emit emit = infNFe.getEmit();
        assertThat(emit.getXNome(), is("TESTE-HOMOLOGAÇÂO"));
    }

    @Test
    public void deveGerarComCNPJ(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        assertThat(infNFe.getEmit().getCNPJ(), is("92638680000191"));
    }

    @Test
    public void deveGerarEmit(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

        assertThat(infNFe.getEmit(), notNullValue());
    }
}
