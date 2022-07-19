package com.vertyce.main;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import com.vertyce.certificado.Certificado;
import com.vertyce.nfe.*;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Certificado certificado = new Certificado();
            ConfiguradorNfe configuradorNfe = new ConfiguradorNfe();

            try {
                br.com.swconsultoria.certificado.Certificado certificadoPfx = certificado.getCertifidoA1Pfx();
                ConfiguracoesNfe configuracoesNfe = configuradorNfe.criarConfiguracoesNfe(certificadoPfx);

                final GeradorInfNfePresenter geradorInfNfePresenter = new GeradorInfNfe();
                TNFe.InfNFe infNFe = geradorInfNfePresenter.gerarInfNFe();

                final GeradorIdePresenter geradorIdePresenter = new GeradorIde();
                final String chave = geradorIdePresenter.gerarIde(infNFe);
                infNFe.setId(chave);

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
