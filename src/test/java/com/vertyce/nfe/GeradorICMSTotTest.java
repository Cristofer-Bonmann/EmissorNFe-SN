package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;
import com.vertyce.builders.ICMSSN101Builder;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.xml.bind.JAXBElement;

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
}
