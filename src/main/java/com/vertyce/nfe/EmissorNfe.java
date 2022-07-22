package com.vertyce.nfe;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.vertyce.certificado.Certificado;
import com.vertyce.nfe.icms.GeradorICMS00;
import com.vertyce.nfe.icms.GeradorICMS00Presenter;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class EmissorNfe implements EmitenteView, DestinatarioView {

    /**
     * Monta e valida uma nota fiscal.
     */
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

            final GeradorDetPresenter geradorDetPresenter = new GeradorDet();
            geradorDetPresenter.gerarDet(infNFe);

            final GeradorProdPresenter geradorProdPresenter = new GeradorProd();
            geradorProdPresenter.gerarProd(infNFe);

            final GeradorImpostoPresenter geradorImpostoPresenter = new GeradorImposto();
            geradorImpostoPresenter.gerarImposto(infNFe);

            final GeradorICMSPresenter geradorICMSPresenter = new GeradorICMS();
            geradorICMSPresenter.gerarICMS(infNFe);

            final GeradorICMS00Presenter geradorICMS00Presenter = new GeradorICMS00();
            geradorICMS00Presenter.geraICMS00(infNFe);

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

        } catch (CertificadoException e) {
            System.out.println("Certificado digital inválido");

        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível carregar os dados do certificado digital!");
        }
    }

    @Override
    public String getSiglaUf() {
        return "AL";
    }
}
