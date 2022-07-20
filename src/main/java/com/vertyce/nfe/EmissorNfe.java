package com.vertyce.nfe;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.vertyce.certificado.Certificado;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class EmissorNfe implements EmitenteView, DestinatarioView {

    // TODO: 20/07/2022 inserir doc
    public void emitirNfe() {
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

            final GeradorEmitPresenter geradorEmitPresenter = new GeradorEmit();
            geradorEmitPresenter.setView(this);
            geradorEmitPresenter.gerarEmit(infNFe);

            final GeradorDestPresenter geradorDestPresenter = new GeradorDest();
            geradorDestPresenter.setView(this);
            geradorDestPresenter.gerarDest(infNFe);

            try {
                TNFe tNFe = new TNFe();
                tNFe.setInfNFe(infNFe);

                TEnviNFe tEnviNFe = new TEnviNFe();
                tEnviNFe.getNFe().add(tNFe);

                System.out.println(XmlNfeUtil.objectToXml(tEnviNFe));
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            } catch (NfeException e) {
                throw new RuntimeException(e);
            }

        } catch (
                CertificadoException e) {
            System.out.println("Certificado digital inválido");

        } catch (
                FileNotFoundException e) {
            System.out.println("Arquivo do certificado digital não foi encontrado!");
        }
    }

    @Override
    public String getSiglaUf() {
        return "AL";
    }
}
