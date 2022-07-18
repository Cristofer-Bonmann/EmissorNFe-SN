package com.vertyce.main;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import com.vertyce.certificado.Certificado;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {



    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Certificado certificado = new Certificado();
            try {
                br.com.swconsultoria.certificado.Certificado certifidoA1Pfx = certificado.getCertifidoA1Pfx();
            } catch (CertificadoException e) {
                System.out.println("Certificado digital inválido");

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo do certificado digital não foi encontrado!");
            }

            System.out.println("Encerrando...");
            System.exit(0);
        });
    }
}
