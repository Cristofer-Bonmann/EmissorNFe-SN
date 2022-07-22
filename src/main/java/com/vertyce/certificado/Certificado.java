package com.vertyce.certificado;

import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Certificado {

    /**
     * Lê o arquivo que contêm a senha do certificado digital e monta objeto Certificado passando o caminho do arquivo e
     * a senha do certificado.
     *
     * @return objeto Certificado
     * @throws CertificadoException disparado quando a senha for incorreta.
     * @throws FileNotFoundException disparado quando: <br>
     * - o caminho do arquivo do certificado digital for incorreto; <br>
     * - caminho do arquivo da senha do certificado.
     */
    public br.com.swconsultoria.certificado.Certificado getCertifidoA1Pfx() throws CertificadoException, FileNotFoundException {
        String caminhoCertificado = System.getProperty("user.dir").concat(File.separator + "certificado_digital.pfx");
        String caminhoPwdCertificado = System.getProperty("user.dir").concat(File.separator + "pwd_certificado.txt");
        String senha = getSenhaPorArquivo(new File(caminhoPwdCertificado));

        return CertificadoService.certificadoPfx(caminhoCertificado, senha);
    }

    /**
     * Lê o arquivo de texto(.txt) que contêm a senha do certificado.
     * @param filePwdCertificado objeto File com arquivo da senha do certificado digital.
     * @return String que representa a senha do certificado.
     * @throws FileNotFoundException disparado quando o arquivo não for encontrado.
     */
    protected String getSenhaPorArquivo(File filePwdCertificado) throws FileNotFoundException {
        String pwd = "";

        File myObj = filePwdCertificado;
        Scanner myReader = new Scanner(myObj);

        String data = "";
        while (myReader.hasNextLine()) {
            data += myReader.nextLine();
        }
        myReader.close();

        pwd = data;

        return pwd;
    }
}
