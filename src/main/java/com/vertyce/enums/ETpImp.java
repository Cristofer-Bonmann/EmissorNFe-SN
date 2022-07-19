package com.vertyce.enums;

/**
 * Formato da impressão da DANFE. Usar para a TAG tpImp <br>
 *
 * 0=Sem geração de DANFE; <br>
 * 1=DANFE normal, Retrato; <br>
 * 2=DANFE normal, Paisagem; <br>
 * 3=DANFE Simplificado; <br>
 * 4=DANFE NFC-e; <br>
 * 5=DANFE NFC-e em mensagem eletrônica (o envio de
 * mensagem eletrônica pode ser feita de forma simultânea com a
 * impressão do DANFE; usar o tpImp=5 quando esta for a única
 * forma de disponibilização do DANFE). <br>
 */
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
