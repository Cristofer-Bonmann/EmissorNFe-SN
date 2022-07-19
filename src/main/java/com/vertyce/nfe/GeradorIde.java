package com.vertyce.nfe;

import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class GeradorIde implements GeradorIdePresenter{
    @Override
    public void gerarIde(TNFe.InfNFe infNFe) {
        TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
        infNFe.setIde(ide);

        final String nNF = "1";
        final String cUF = "27";
        final String cNF = ChaveUtil.completarComZerosAEsquerda(nNF, 8);
        final String natOp = "VENDA DE MERCADORIA PRODUZIDA OU ADQUIRIDA POR TERCEIROS";
        final String mod = DocumentoEnum.NFE.getModelo();
        final String serie = "1";
        String dhEmi = null;
        String dhSaiEnt = null;
        final LocalDateTime localDateTimeAgora = getLocalDateTimeAgora();
        if (localDateTimeAgora != null) {
            dhEmi = XmlNfeUtil.dataNfe(localDateTimeAgora);
            dhSaiEnt = dhEmi;
        }
        final String tpNF = "1";
        final String idDest = null;
        final String cMunFG = "2700102";
        final String tpImp = "1";
        final String tpEmis = "1";

        ide.setCUF(cUF);
        ide.setCNF(cNF);
        ide.setNatOp(natOp);
        ide.setMod(mod);
        ide.setSerie(serie);
        ide.setNNF(nNF);
        ide.setDhEmi(dhEmi);
        ide.setDhSaiEnt(dhSaiEnt);
        ide.setTpNF(tpNF);
        ide.setIdDest(idDest);
        ide.setCMunFG(cMunFG);
        ide.setTpImp(tpImp);
        ide.setTpEmis(tpEmis);
    }

    /**
     * @return objeto LocalDateTime que representa a data atual.
     */
    protected LocalDateTime getLocalDateTimeAgora(){
        return LocalDateTime.now(ZoneId.systemDefault());
    }
}
