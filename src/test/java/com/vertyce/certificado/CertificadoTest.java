package com.vertyce.certificado;

import com.vertyce.util.Util;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static java.io.File.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CertificadoTest {

    @Spy
    private Certificado certificado;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarSenhaCertificado() throws FileNotFoundException {
        File tempFile = Util.criaEscreveArquivoTemp("pwd_certificado", ".txt", "1111");

        final String pdwCertificado = certificado.getSenhaPorArquivo(tempFile);

        assertThat(pdwCertificado, is("1111"));
    }
}
