package com.vertyce.nfe;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class GeradorEmitTest {

    @Spy
    private GeradorEmit geradorEmit;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void gerarComCNPJ(){

    }
}
