package com.vertyce.nfe;

import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.vertyce.enums.*;
import com.vertyce.sistema.Sistema;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

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
        final String tpImp = ETpImp.RETRATO.getCodigo();
        final String tpEmis = ETpEmis.NORMAL.getCodigo();
        final String cnpj = "92638680000191";
        final EstadosEnum estadoEnum = EstadosEnum.getByCodigoIbge(cUF);

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

        Map<String, String> dadosDaChave = getDadosDaChave(ide, cnpj, localDateTimeAgora);
        if (dadosDaChave != null) {
            ide.setCDV(dadosDaChave.get("cdv"));
        }

        ide.setTpAmb(AmbienteEnum.HOMOLOGACAO.getCodigo());
        ide.setFinNFe(EFinNFe.NORMAL.getCodigo());
        ide.setIndFinal(EIndFinal.NORMAL.getCodigo());
        ide.setIndPres(EIndPres.NAO_PRESENCIAL_OUTROS.getCodigo());
        ide.setProcEmi(EProcEmi.COM_APP_CONTRIB.getCodigo());
        ide.setVerProc(getVersaoSistema());
    }

    // TODO: 19/07/2022 adicionar doc
    protected Map<String, String> getDadosDaChave(TNFe.InfNFe.Ide ide, String cnpj, LocalDateTime dhEmi){
        String chave = null;
        String cdv = null;

        if (ide != null && cnpj != null && dhEmi != null) {

            final String cUF = ide.getCUF();
            final String mod = ide.getMod();
            final String serie = ide.getSerie();
            final String nNF = ide.getNNF();
            final String tpEmis = ide.getTpEmis();
            final String cnf = ide.getCNF();

            final EstadosEnum estadoEnum = EstadosEnum.getByCodigoIbge(cUF != null ? cUF : "27");
            final Integer serieInt = serie != null ? Integer.parseInt(serie) : 0;
            final Integer nNFInt = nNF != null ? Integer.parseInt(nNF) : 0;
            final String cNF = cnf != null ? cnf : "00000000";

            ChaveUtil chaveUtil = new ChaveUtil(
                    estadoEnum,
                    cnpj,
                    mod,
                    serieInt,
                    nNFInt,
                    tpEmis,
                    cNF,
                    dhEmi);

            chave = chaveUtil.getChaveNF();
            cdv = chaveUtil.getDigitoVerificador();
        }

        final HashMap<String, String> dadosChave = new HashMap<>();
        dadosChave.put("cdv", cdv);
        dadosChave.put("chave", chave);

        return dadosChave;
    }

    /**
     * @return objeto LocalDateTime que representa a data atual.
     */
    protected LocalDateTime getLocalDateTimeAgora(){
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    // TODO: 19/07/2022 inserir doc
    protected String getVersaoSistema() {
        return Sistema.VERSAO;
    }
}
