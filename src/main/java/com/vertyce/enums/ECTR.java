package com.vertyce.enums;

/**
 * Enumeration com informações do CRT do emitente da nota fiscal. Usar na TAG CRT. <br>
 * 1=Simples Nacional; <br>
 * 2=Simples Nacional, excesso sublimite de receita bruta; <br>
 * 3=Regime Normal. (v2.0).
 */
public enum ECTR {

    SIMPLES_NACIONAL("1", "Simples Nacional"),
    SIMPLE_NACIONAL_EXCESSO("2", "Simples Nacional, excesso sublimite de receita bruta"),
    REGIME_NORMAL("3", "Regime Normal. (v2.0)");

    private String codigo;
    private String descricao;

    ECTR(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
