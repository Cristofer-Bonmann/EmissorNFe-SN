package com.vertyce.certificado;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.File;
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
    public void deveRetornarSenhaCertificado(){
        File tempFile = null;
        try {
            tempFile = createTempFile("pwd_certificado", ".txt");

            try {
                FileWriter myWriter = new FileWriter(tempFile.getAbsolutePath());
                myWriter.write("1111");
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final String pdwCertificado = certificado.getSenhaPorArquivo(tempFile);

        assertThat(pdwCertificado, is("1111"));
    }
}
