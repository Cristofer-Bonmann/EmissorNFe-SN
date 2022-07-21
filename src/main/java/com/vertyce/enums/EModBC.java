package com.vertyce.enums;

/**
 * Informações da modalidade da base de cálculo do ICMS. Usar na TAG <b>modBC</b>. <br>
 * 0=Margem Valor Agregado (%);<br>
 * 1=Pauta (Valor);<br>
 * 2=Preço Tabelado Máx. (valor);<br>
 * 3=Valor da operação.
 */
public enum EModBC {

    MARGEM_VALOR_AGRAGADO("0", "Margem Valor Agregado (%)"),
    PAUTA_VALOR("1", "Pauta (Valor)"),
    PRECO_TABELADO_MAX("2", "Preço Tabelado Máx. (valor)"),
    VALOR_OPERACAO("3", "Valor da operação");

    private String codigo;
    private String descricao;

    EModBC(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
