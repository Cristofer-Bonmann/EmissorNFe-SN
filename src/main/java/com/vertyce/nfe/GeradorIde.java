package com.vertyce.nfe;

import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.ChaveUtil;

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

        ide.setCUF(cUF);
        ide.setCNF(cNF);
        ide.setNatOp(natOp);
        ide.setMod(mod);
        ide.setSerie(serie);
        ide.setNNF(nNF);
    }
}
