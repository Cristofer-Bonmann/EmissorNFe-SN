package com.vertyce.enums;

/**
 * Enumeration com informação do processo de emissão da NF-e. Usar na TAG <b>procEmi</b>.  <br>
 * 0=Emissão de NF-e com aplicativo do contribuinte; <br>
 * 1=Emissão de NF-e avulsa pelo Fisco; <br>
 * 2=Emissão de NF-e avulsa, pelo contribuinte com seu <br>
 * certificado digital, através do site do Fisco; <br>
 * 3=Emissão NF-e pelo contribuinte com aplicativo fornecido pelo
 * Fisco.
 */
public enum EProcEmi {

    COM_APP_CONTRIB("0", "Emissão de NF-e com aplicativo do contribuinte"),
    AVULSA_FISCO("1", "Emissão de NF-e avulsa pelo Fisco"),
    AVULSA_CERT_FISCAL("2", "Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco"),
    COM_APP_FISCO("3", "Emissão NF-e pelo contribuinte com aplicativo fornecido pelo Fisco");

    private String codigo;
    private String descricao;

    EProcEmi(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
