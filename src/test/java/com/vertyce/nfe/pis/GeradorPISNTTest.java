package com.vertyce.nfe.pis;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import com.vertyce.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.xml.bind.JAXBElement;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeradorPISNTTest {

    @Spy
    private GeradorPISNT geradorPisNT;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveGerarPISNT(){
        final InfNFe infNFe = Util.getInfNFeComImposto();
        Util.addICMS(infNFe);
        Util.addPIS(infNFe);

        geradorPisNT.gerarPisNT(infNFe);

        PIS pis = Util.getPIS(infNFe);
        assertThat(pis.getPISNT(), notNullValue());
    }
}
