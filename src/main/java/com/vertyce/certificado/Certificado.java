package com.vertyce.certificado;

import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;

import java.io.FileNotFoundException;

public class Certificado {

    public br.com.swconsultoria.certificado.Certificado getCertifidoA1Pfx() throws CertificadoException, FileNotFoundException {
        String caminhoCertificado = "/home/vertyce/IdeaProjects/EmissorNFeSN/certificado_digital.pfx";
        String senha = "1234";

        return CertificadoService.certificadoPfx(caminhoCertificado, senha);
    }
}
