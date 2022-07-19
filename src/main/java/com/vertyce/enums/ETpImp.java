package com.vertyce.enums;

public enum ETpImp {
    SEM_DANFE("0", "Sem geração de DANFE"),
    RETRATO("1", "DANFE normal, Retrato"),
    PAISAGEM("2", "DANFE normal, Paisagem"),
    SIMPLIFICADO("3", "DANFE Simplificado"),
    NFEC_E("4", "DANFE NFC-e"),
    NFCE_E_SIMUL("5", "DANFE NFC-e em mensagem eletrônica");

    private String codigo;
    private String descricao;

    ETpImp(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
