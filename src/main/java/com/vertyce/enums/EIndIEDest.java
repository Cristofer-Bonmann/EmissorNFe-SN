package com.vertyce.enums;

/**
 * Enumeration com informações de indicador da IE do destinatário. Usar na TAG <b>indIEDest</b> <br>
 * 1=Contribuinte ICMS (informar a IE do destinatário); <br>
 * 2=Contribuinte isento de Inscrição no cadastro de Contribuintes do ICMS; <br>
 * 9=Não Contribuinte, que pode ou não possuir Inscrição
 * Estadual no Cadastro de Contribuintes do ICMS.
 * Nota 1: No caso de NFC-e informar indIEDest=9 e não informar
 * a tag IE do destinatário;
 * Nota 2: No caso de operação com o Exterior informar
 * indIEDest=9 e não informar a tag IE do destinatário;
 * Nota 3: No caso de Contribuinte Isento de Inscrição
 * (indIEDest=2), não informar a tag IE do destinatário.
 */
public enum EIndIEDest {

    CONTRIBUINTE_ICMS("1", "Contribuinte ICMS (informar a IE do destinatário)"),
    CONTRIBUINTE_ISENTO("2", "Contribuinte isento de Inscrição no cadastro de Contribuintes do ICMS"),
    NAO_CONTRIBUINTE("9", "Não Contribuinte, que pode ou não possuir Inscrição Estadual no Cadastro de " +
            "Contribuintes do ICMS. Nota 1: No caso de NFC-e informar indIEDest=9 e " +
            "não informar a tag IE do destinatário;Nota 2: No caso de operação com o " +
            "Exterior informar indIEDest=9 e não informar a tag IE do destinatário; " +
            "Nota 3: No caso de Contribuinte Isento de Inscrição (indIEDest=2), não " +
            "informar a tag IE do destinatário");

    private String codigo;
    private String descricao;

    EIndIEDest(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
