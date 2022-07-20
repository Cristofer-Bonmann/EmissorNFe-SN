package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnderEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUfEmi;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorEmitTest {

    @Spy
    private GeradorEmit geradorEmit;

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
    public void deveGerarComUF(){
        TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorEmit.gerarEmit(infNFe);

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
    public void deveGerarComNome(){
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
