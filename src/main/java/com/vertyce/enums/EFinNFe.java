package com.vertyce.enums;

/**
 * Enumeration com informações da finalidade de emissão da NF-e. Usar na TAG finNFe. <br>
 * 1=NF-e normal; <br>
 * 2=NF-e complementar; <br>
 * 3=NF-e de ajuste; <br>
 * 4=Devolução de mercadoria. <br>
 */
public enum EFinNFe {
    NORMAL("1", "NF-e normal"),
    COMPLEMENTAR("2", "NF-e complementar"),
    AJUSTE("3", "NF-e de ajuste"),
    DEVOLUCAO_MERCADORIA("4", "Devolução de mercadoria");

    private String codigo;
    private String descricao;

    EFinNFe(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
