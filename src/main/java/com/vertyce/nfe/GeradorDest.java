package com.vertyce.nfe;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TEndereco;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUf;
import com.vertyce.enums.EIndIEDest;

public class GeradorDest implements com.vertyce.nfe.GeradorDestPresenter {

    private DestinatarioView view;

    // TODO: 20/07/2022 inserir o doc
    @Override
    public void gerarDest(TNFe.InfNFe infNFe) {
        final String cnpj = "92638680000191";
        final String idEstrangeiro = null;
        final String xNome = "TESTE-HOMOLOGAÇÂO";
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
        final String fone = null;
        final String ie = "248166980";
        final String indIEDest = EIndIEDest.CONTRIBUINTE_ICMS.getCodigo();
        final String isUF = null;
        final String im = null;
        final String email = null;

        final TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();
        infNFe.setDest(dest);

        dest.setCNPJ(cnpj);
        dest.setIdEstrangeiro(idEstrangeiro);
        dest.setXNome(xNome);

        TEndereco enderDest = new TEndereco();
        dest.setEnderDest(enderDest);

        enderDest.setXLgr(xLgr);
        enderDest.setNro(nro);
        enderDest.setXCpl(xCpl);
        enderDest.setXBairro(xBairro);
        enderDest.setCMun(cMun);
        enderDest.setXMun(xMun);

        if (uf != null) {
            TUf tUf = TUf.valueOf(uf);
            enderDest.setUF(tUf);
        }

        enderDest.setCEP(cep);
        enderDest.setCPais(cPais);
        enderDest.setXPais(xPais);
        enderDest.setFone(fone);

        dest.setIE(ie);
        dest.setIndIEDest(indIEDest);
        dest.setISUF(isUF);
        dest.setIM(im);
        dest.setEmail(email);
    }
}
