package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;

import java.util.List;

public class GeradorProd implements GeradorProdPresenter{
    // TODO: 20/07/2022 inserir doc
    @Override
    public void gerarProd(TNFe.InfNFe infNFe) {
        List<TNFe.InfNFe.Det> dets = infNFe.getDet();

        dets.stream().forEach(det -> {
            final String cProd = "1";
            final String cean = "1234567891234";
            final String xProd = "PRODUTO PARA TESTE - HOMOLOGAÇÃO";
            final String ncm = "29313997";
            final String exitIPI = null;
            final String cfop = "5102";
            final String uCom = "UNID";
            final String qCom = "1.0000";
            final String vUnCom = "1.0000000000";
            final String vProd = "1.00";
            final String ceanTrib = cean;
            final String uTrib = uCom;
            final String qTrib = qCom;
            final String vFrete = null;
            final String vSeg = null;
            final String vDesc = null;
            final String vOutro = null;
            final String indTot = "1";

            final TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
            det.setProd(prod);

            prod.setCProd(cProd);
            prod.setCEAN(cean);
            prod.setXProd(xProd);
            prod.setNCM(ncm);
            prod.setEXTIPI(exitIPI);
            prod.setCFOP(cfop);
            prod.setUCom(uCom);
            prod.setQCom(qCom);
            prod.setVUnCom(vUnCom);
            prod.setVProd(vProd);
            prod.setCEANTrib(ceanTrib);
            prod.setUTrib(uTrib);
            prod.setQTrib(qTrib);
            prod.setVFrete(vFrete);
            prod.setVSeg(vSeg);
            prod.setVDesc(vDesc);
            prod.setVOutro(vOutro);
            prod.setIndTot(indTot);
        });
    }
}
