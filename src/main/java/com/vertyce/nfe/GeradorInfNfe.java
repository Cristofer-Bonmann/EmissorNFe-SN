package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.ConstantesUtil;

public class GeradorInfNfe implements GeradorInfNfePresenter{
    /**
     * Gera e retorna grupo raíz das informações da NF-e.
     * @return objeto InfNFe.
     */
    @Override
    public TNFe.InfNFe gerarInfNFe() {
        TNFe.InfNFe infNFe = new TNFe.InfNFe();
        infNFe.setVersao(ConstantesUtil.VERSAO.NFE);
        infNFe.setId("NFe00000000000000000000000000000000000000000000");
        return infNFe;
    }
}
