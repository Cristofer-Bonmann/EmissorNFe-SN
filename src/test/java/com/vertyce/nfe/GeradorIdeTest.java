package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class GeradorIdeTest {

    @Spy
    private GeradorIde geradorIde;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComTpEmis(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getTpEmis(), is("1"));
    }

    @Test
    public void deveGerarComTpImp(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getTpImp(), is("1"));
    }

    @Test
    public void deveGerarComCMunFG(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getCMunFG(), is("2700102"));
    }

    @Test
    public void deveGerarComTpNF(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();

        geradorIde.gerarIde(infNFe);

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getTpNF(), is("1"));
    }

    @Test
    public void deveGerarComDHSaiEnt(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        final String str = "2022-01-01 12:30:00";
        final String pattern = "yyyy-MM-dd HH:mm:ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/Sao_Paulo"));
        final LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        doReturn(dateTime).when(geradorIde).getLocalDateTimeAgora();
        geradorIde.gerarIde(infNFe);

        verify(geradorIde).getLocalDateTimeAgora();

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getDhSaiEnt(), is("2022-01-01T12:30:00-03:00"));
    }

    @Test
    public void deveGerarComDHEmit(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        final String str = "2022-01-01 12:30:00";
        final String pattern = "yyyy-MM-dd HH:mm:ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/Sao_Paulo"));
        final LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        doReturn(dateTime).when(geradorIde).getLocalDateTimeAgora();
        geradorIde.gerarIde(infNFe);

        verify(geradorIde).getLocalDateTimeAgora();

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getDhEmi(), is("2022-01-01T12:30:00-03:00"));
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

    @Test
    public void deveRetornarLocalDateTimeAtual(){
        LocalDateTime localDateTimeAgora = geradorIde.getLocalDateTimeAgora();

        assertThat(localDateTimeAgora, notNullValue());
    }
}
