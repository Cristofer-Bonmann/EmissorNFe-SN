package com.vertyce.nfe.cofins;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;
import com.vertyce.util.Util;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorCOFINSNTTest {

    @Spy
    private GeradorCOFINSNT geradorCOFINSNT;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarComCST(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);
        Util.addPIS(infNFe);
        Util.addCOFINS(infNFe);

        geradorCOFINSNT.gerarCOFINSNT(infNFe);

        final COFINSNT cofinsnt = Util.getCOFINS(infNFe).getCOFINSNT();
        assertThat(cofinsnt.getCST(), is("04"));
    }

    @Test
    public void deveGerarCOFINSNT(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);
        Util.addPIS(infNFe);
        Util.addCOFINS(infNFe);

        geradorCOFINSNT.gerarCOFINSNT(infNFe);

        final COFINSNT cofinsnt = Util.getCOFINS(infNFe).getCOFINSNT();
        assertThat(cofinsnt, notNullValue());
    }
}
