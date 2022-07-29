package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;
import com.vertyce.builders.ICMSSN101Builder;
import com.vertyce.builders.PISAliqBuilder;
import com.vertyce.enums.EICMSTotMethod;
import com.vertyce.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorICMSTotTest {

    @Spy
    private GeradorICMSTot geradorICMSTot;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComVPIS(){
        final InfNFe infNFe = PISAliqBuilder.getPISAliq().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVPIS(), is("0.50"));
    }

    @Test
    public void deveGerarComVIPI(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVIPI(), is("0.00"));
    }

    @Test
    public void deveGerarComVII(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVII(), is("0.00"));
    }

    @Test
    public void deveGerarComVDesc(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVDesc(), is("5.00"));
    }

    @Test
    public void deveGerarComVSeg(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVSeg(), is("5.00"));
    }

    @Test
    public void deveGerarComVFrete(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVFrete(), is("5.00"));
    }

    @Test
    public void deveGerarComVProd(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVProd(), is("100.00"));
    }

    @Test
    public void deveGerarComVST(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVST(), is("0.00"));
    }

    @Test
    public void deveGerarComVBCST(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVBCST(), is("0.00"));
    }

    @Test
    public void deveGerarComVICMSDeson(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVICMSDeson(), is("0.00"));
    }

    @Test
    public void deveGerarComVICMS(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVICMS(), is("0.00"));
    }

    @Test
    public void deveGerarComVBC(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVBC(), is("0.00"));
    }

    @Test
    public void naoDeveGerarSemTotal(){
        final InfNFe infNFe = new InfNFe();

        geradorICMSTot.gerarICMSTot(infNFe);

        assertThat(infNFe.getTotal(), nullValue());
    }

    @Test
    public void deveGerarICMSTot(){
        final InfNFe infNFe = new InfNFe();
        infNFe.setTotal(new Total());

        geradorICMSTot.gerarICMSTot(infNFe);

        assertThat(infNFe.getTotal().getICMSTot(), notNullValue());
    }

    @Test
    public void deveRetornarTotalVProdZeradoComVProdFormatoInvalido(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().setVProd("abcd").get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVProd(), is("0.00"));
    }

    @Test
    public void deveRetornarTotalVProdZeradoComVProdNulo(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().setVProdNull().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVProd(), is("0.00"));
    }

    @Test
    public void deveRetornarVProdZeradoSemProds(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().semProd().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVProd(), is("0.00"));
    }

    @Test
    public void deveRetornarVProdZeradoSemDets(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().semDet().get();

        final Total total = new Total();
        infNFe.setTotal(total);

        geradorICMSTot.gerarICMSTot(infNFe);

        final Total.ICMSTot icmsTot = infNFe.getTotal().getICMSTot();
        assertThat(icmsTot.getVProd(), is("0.00"));
    }

    @Test
    public void deveRetornarValorTotalNuloComMethodNaoEncontrado(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        String totalPorCampo = geradorICMSTot.getTotalPorCampo(infNFe.getDet(), "");

        assertThat(totalPorCampo, nullValue());
    }

    @Test
    public void deveRetornarValorVProd(){
        final InfNFe infNFe = ICMSSN101Builder.getICMSSN101().get();

        final String vProd = geradorICMSTot.getTotalPorCampo(infNFe.getDet(), "VProd");

        assertThat(vProd, is("100.00"));
    }
}
