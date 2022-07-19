package com.vertyce.enums;

/**
 * Enumeration das informações de indicador de operação para consumidor. Usar na TAG <b>indFinal</b>. <br>
 * 0=Normal; <br>
 * 1=Consumidor final; <br>
 */
public enum EIndFinal {

    NORMAL("0", "Normal"),
    CONSUMIDOR_FINAL("1", "Consumidor Final");

    private String codigo;
    private String descricao;

    EIndFinal(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
