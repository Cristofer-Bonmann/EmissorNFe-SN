package com.vertyce.nfe;

import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import com.vertyce.enums.ETpEmis;
import com.vertyce.util.Util;
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
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GeradorIdeTest {

    @Spy
    private GeradorIde geradorIde;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComCDV(){
        final TNFe.InfNFe infNFe = new TNFe.InfNFe();
        final HashMap<String, String> dadosChave = new HashMap<>();
        dadosChave.put("cdv", "1");

        doReturn(dadosChave).when(geradorIde).getDadosDaChave(any(), any(), any());
        geradorIde.gerarIde(infNFe);

        verify(geradorIde).getDadosDaChave(any(), any(), any());

        TNFe.InfNFe.Ide ide = infNFe.getIde();
        assertThat(ide.getCDV(), is("1"));
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
        LocalDateTime dateTime = Util.strToLocalDateTime(str);

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
    public void deveRetornarDadosChavePropriedadeIdeNulas(){
        final String cnpj = "92638680000191";
        final TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
        ide.setCUF(null);
        ide.setMod(null);
        ide.setSerie(null);
        ide.setNNF(null);
        ide.setTpEmis(null);
        ide.setCNF(null);
        ide.setDhEmi("2022-01-01 12:30:00");
        LocalDateTime localDateTime = Util.strToLocalDateTime(ide.getDhEmi());

        Map<String, String> dadosDaChave = geradorIde.getDadosDaChave(ide, cnpj, localDateTime);

        final String chave = dadosDaChave.get("chave");
        final String cdv = dadosDaChave.get("cdv");

        System.out.println(chave);
        System.out.println(cdv);

        assertThat(chave, notNullValue());
        assertThat(cdv, notNullValue());
    }

    @Test
    public void deveRetornarDadosChaveComDataEmissaoNula(){
        final String cnpj = "92638680000191";
        final TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
        ide.setCUF("27");
        ide.setMod(DocumentoEnum.NFE.getModelo());
        ide.setSerie("1");
        ide.setNNF("1");
        ide.setTpEmis(ETpEmis.NORMAL.getCodigo());
        ide.setCNF("00000001");
        ide.setDhEmi("2022-01-01 12:30:00");
        LocalDateTime localDateTime = null;

        Map<String, String> dadosDaChave = geradorIde.getDadosDaChave(ide, cnpj, localDateTime);

        final String chave = dadosDaChave.get("chave");
        final String cdv = dadosDaChave.get("chave");

        assertThat(chave, nullValue());
        assertThat(cdv, nullValue());
    }

    @Test
    public void deveRetornarDadosChaveComCNPJNulo(){
        final String cnpj = null;
        final TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
        ide.setCUF("27");
        ide.setMod(DocumentoEnum.NFE.getModelo());
        ide.setSerie("1");
        ide.setNNF("1");
        ide.setTpEmis(ETpEmis.NORMAL.getCodigo());
        ide.setCNF("00000001");
        ide.setDhEmi("2022-01-01 12:30:00");
        LocalDateTime localDateTime = Util.strToLocalDateTime(ide.getDhEmi());

        Map<String, String> dadosDaChave = geradorIde.getDadosDaChave(ide, cnpj, localDateTime);

        final String chave = dadosDaChave.get("chave");
        final String cdv = dadosDaChave.get("chave");

        assertThat(chave, nullValue());
        assertThat(cdv, nullValue());
    }

    @Test
    public void deveRetornarDadosDaChaveComIdeNulo(){
        final String cnpj = "92638680000191";
        final TNFe.InfNFe.Ide ide = null;
        LocalDateTime localDateTime = Util.strToLocalDateTime("2022-01-01 12:30:00");

        Map<String, String> dadosDaChave = geradorIde.getDadosDaChave(ide, cnpj, localDateTime);

        final String chave = dadosDaChave.get("chave");
        final String cdv = dadosDaChave.get("chave");

        assertThat(chave, nullValue());
        assertThat(cdv, nullValue());
    }

    @Test
    public void deveRetornarChaveNfe(){
        final String cnpj = "92638680000191";
        final TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
        ide.setCUF("27");
        ide.setMod(DocumentoEnum.NFE.getModelo());
        ide.setSerie("1");
        ide.setNNF("1");
        ide.setTpEmis(ETpEmis.NORMAL.getCodigo());
        ide.setCNF("00000001");
        ide.setDhEmi("2022-01-01 12:30:00");
        LocalDateTime localDateTime = Util.strToLocalDateTime(ide.getDhEmi());

        Map<String, String> dadosDaChave = geradorIde.getDadosDaChave(ide, cnpj, localDateTime);

        final String chave = dadosDaChave.get("chave");
        assertThat(chave, is("NFe27220192638680000191550010000000011000000012"));
    }

    @Test
    public void deveRetornarCodigoDigitoVerificador(){
        final String cnpj = "92638680000191";
        final TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
        ide.setCUF("27");
        ide.setMod(DocumentoEnum.NFE.getModelo());
        ide.setSerie("1");
        ide.setNNF("1");
        ide.setTpEmis(ETpEmis.NORMAL.getCodigo());
        ide.setCNF("00000001");
        ide.setDhEmi("2022-01-01 12:30:00");
        LocalDateTime localDateTime = Util.strToLocalDateTime(ide.getDhEmi());

        Map<String, String> dadosDaChave = geradorIde.getDadosDaChave(ide, cnpj, localDateTime);

        final String cdv = dadosDaChave.get("cdv");
        assertThat(cdv, is("2"));
    }

    @Test
    public void deveRetornarLocalDateTimeAtual(){
        LocalDateTime localDateTimeAgora = geradorIde.getLocalDateTimeAgora();

        assertThat(localDateTimeAgora, notNullValue());
    }
}
