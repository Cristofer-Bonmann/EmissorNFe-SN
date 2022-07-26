package com.vertyce.nfe;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.vertyce.certificado.Certificado;
import com.vertyce.nfe.cofins.GeradorCOFINS;
import com.vertyce.nfe.cofins.GeradorCOFINSNT;
import com.vertyce.nfe.cofins.IGeradorCOFINS;
import com.vertyce.nfe.cofins.IGeradorCOFINSNT;
import com.vertyce.nfe.icmssn.GeradorICMSSN101;
import com.vertyce.nfe.icmssn.IGeradorICMSSN101;
import com.vertyce.nfe.pis.GeradorPIS;
import com.vertyce.nfe.pis.GeradorPISNT;
import com.vertyce.nfe.pis.IGeradorPIS;
import com.vertyce.nfe.pis.IGeradorPISNT;

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

            final IGeradorInfNfe iGeradorInfNfe = new GeradorInfNfe();
            TNFe.InfNFe infNFe = iGeradorInfNfe.gerarInfNFe();

            final IGeradorIde iGeradorIde = new GeradorIde();
            final String chave = iGeradorIde.gerarIde(infNFe);
            infNFe.setId(chave);

            final IGeradorEmit iGeradorEmit = new GeradorEmit();
            iGeradorEmit.setView(this);
            iGeradorEmit.gerarEmit(infNFe);

            final IGeradorDest iGeradorDest = new GeradorDest();
            iGeradorDest.setView(this);
            iGeradorDest.gerarDest(infNFe);

            final IGeradorDet iGeradorDet = new GeradorDet();
            iGeradorDet.gerarDet(infNFe);

            final IGeradorProd iGeradorProd = new GeradorProd();
            iGeradorProd.gerarProd(infNFe);

            final IGeradorImposto iGeradorImposto = new GeradorImposto();
            iGeradorImposto.gerarImposto(infNFe);

            final IGeradorICMS iGeradorICMS = new GeradorICMS();
            iGeradorICMS.gerarICMS(infNFe);

            final IGeradorICMSSN101 igeradorICMSSN101 = new GeradorICMSSN101();
            igeradorICMSSN101.gerarICMSSN101(infNFe);

            final IGeradorPIS iGeradorPIS = new GeradorPIS();
            iGeradorPIS.gerarPIS(infNFe);

            final IGeradorPISNT iGeradorPISNT = new GeradorPISNT();
            iGeradorPISNT.gerarPISNT(infNFe);

            final IGeradorCOFINS iGeradorCOFINS = new GeradorCOFINS();
            iGeradorCOFINS.gerarCOFINS(infNFe);

            final IGeradorCOFINSNT iGeradorCOFINSNT = new GeradorCOFINSNT();
            iGeradorCOFINSNT.gerarCOFINSNT(infNFe);

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
