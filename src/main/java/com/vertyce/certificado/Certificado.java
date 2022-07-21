package com.vertyce.certificado;

import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Certificado {

    // TODO: 21/07/2022 inserir doc
    public br.com.swconsultoria.certificado.Certificado getCertifidoA1Pfx() throws CertificadoException, FileNotFoundException {
        String caminhoCertificado = "/home/vertyce/IdeaProjects/EmissorNFeSN/certificado_digital.pfx";
        String senha = "1234";

        return CertificadoService.certificadoPfx(caminhoCertificado, senha);
    }

    // TODO: 21/07/2022 inserir doc
    public String getSenhaPorArquivo(File filePwdCertificado) {
        String pwd = "";

        try {
            File myObj = filePwdCertificado;
            Scanner myReader = new Scanner(myObj);

            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();

            pwd = data;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return pwd;
    }
}
