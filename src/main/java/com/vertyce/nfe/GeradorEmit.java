package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnderEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUfEmi;

public class GeradorEmit implements com.vertyce.nfe.GeradorEmitPresenter{

    private EmitenteView view;

    // TODO: 19/07/2022 inserir doc
    @Override
    public void gerarEmit(TNFe.InfNFe infNFe) {
        final String cnpj = "92638680000191";
        final String xNome = "TESTE-HOMOLOGAÇÂO";
        final String xFant = null;
        final String xLgr = "LOGRADOURO DE TESTE";
        final String nro = "999";
        final String xCpl = "SEM COMPLEMENTO";
        final String xBairro = "BAIRRO DE TESTE";
        final String cMun = "2700102";
        final String xMun = "Água Branca";
        final String uf = view.getSiglaUf();
        final String cep = "57490000";
        final String cPais = null;
        final String xPais = null;
        final String ie = "248166980";
        final String ieST = null;
        final String im = null;
        final String cnae = null;

        TNFe.InfNFe.Emit emit = new TNFe.InfNFe.Emit();
        infNFe.setEmit(emit);

        emit.setCNPJ(cnpj);
        emit.setXNome(xNome);
        emit.setXFant(xFant);

        final TEnderEmi tEnderEmi = new TEnderEmi();
        emit.setEnderEmit(tEnderEmi);

        tEnderEmi.setXLgr(xLgr);
        tEnderEmi.setNro(nro);
        tEnderEmi.setXCpl(xCpl);
        tEnderEmi.setXBairro(xBairro);
        tEnderEmi.setCMun(cMun);
        tEnderEmi.setXMun(xMun);

        if (uf != null) {
            TUfEmi tUfEmi = TUfEmi.valueOf(uf);
            tEnderEmi.setUF(tUfEmi);
        }

        tEnderEmi.setCEP(cep);
        tEnderEmi.setCPais(cPais);
        tEnderEmi.setXPais(xPais);

        emit.setIE(ie);
        emit.setIEST(ieST);
        emit.setIM(im);
        emit.setCNAE(cnae);
    }
}
