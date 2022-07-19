package com.vertyce.enums;

/**
 * Enumeration com informação Indicador de presença do comprador no estabelecimento comercial no momento da
 * operação. Usar na TAG <b>indPres</b>. <br>
 *
 * 0=Não se aplica (por exemplo, Nota Fiscal complementar ou de ajuste); <br>
 * 1=Operação presencial; <br>
 * 2=Operação não presencial, pela Internet; <br>
 * 3=Operação não presencial, Teleatendimento; <br>
 * 4=NFC-e em operação com entrega a domicílio; <br>
 * 9=Operação não presencial, outros.
 */
public enum EIndPres {

    NAO_APLICA("0", "Não se aplica (por exemplo, Nota Fiscal complementar ou de ajuste)"),
    PRESENCIAL("1", "Operação presencial"),
    NAO_PRESENCIAL_INTERNET("2", "Operação não presencial, pela Internet"),
    NAO_PRESENCIAL_POR_TELE("3", "Operação não presencial, Teleatendimento"),
    COM_ENTREGA("4", "NFC-e em operação com entrega a domicílio"),
    NAO_PRESENCIAL_OUTROS("9", "Operação não presencial, outros");

    private String codigo;
    private String descricao;

    EIndPres(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
