package com.vertyce.nfe.cofins;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import com.vertyce.util.Util;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorCOFINSTest {

    @Spy
    private GeradorCOFINS geradorCOFINS;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void naoDeveGerarSemImposto(){
        final InfNFe infNFe = new InfNFe();
        infNFe.getDet().add(new InfNFe.Det());

        geradorCOFINS.gerarCOFINS(infNFe);

        final COFINS cofins = Util.getCOFINS(infNFe);
        assertThat(cofins, CoreMatchers.nullValue());
    }

    @Test
    public void deveGerarCOFINS(){
        final InfNFe infNFe = Util.getInfNFeComImposto();

        geradorCOFINS.gerarCOFINS(infNFe);

        final COFINS cofins = Util.getCOFINS(infNFe);
        assertThat(cofins, notNullValue());
    }
}
