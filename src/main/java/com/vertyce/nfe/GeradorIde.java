package com.vertyce.nfe;

import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.vertyce.enums.ETpEmis;
import com.vertyce.enums.ETpImp;

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
    }

    // TODO: 19/07/2022 adicionar doc
    protected Map<String, String> getDadosDaChave(TNFe.InfNFe.Ide ide, String cnpj, LocalDateTime dhEmit){
        ChaveUtil chaveUtil = new ChaveUtil(
                EstadosEnum.getByCodigoIbge(ide.getCUF()),
                cnpj,
                ide.getMod(),
                Integer.parseInt(ide.getSerie()),
                Integer.parseInt(ide.getNNF()),
                ide.getTpEmis(),
                ide.getCNF(),
                dhEmit);

        String chave = chaveUtil.getChaveNF();
        String cdv = chaveUtil.getDigitoVerificador();

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
}
