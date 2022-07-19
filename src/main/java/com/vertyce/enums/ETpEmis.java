package com.vertyce.enums;

/**
 * Tipo de Emissão da NF-e. Usar para a TAG tpEmis  <br>
 *
 * 1=Emissão normal (não em contingência);  <br>
 * 2=Contingência FS-IA, com impressão do DANFE em formulário
 * de segurança;  <br>
 * 3=Contingência SCAN (Sistema de Contingência do Ambiente
 * Nacional);  <br>
 * 4=Contingência DPEC (Declaração Prévia da Emissão em
 * Contingência);  <br>
 * 5=Contingência FS-DA, com impressão do DANFE em
 * formulário de segurança;  <br>
 * 6=Contingência SVC-AN (SEFAZ Virtual de Contingência do
 * AN);  <br>
 * 7=Contingência SVC-RS (SEFAZ Virtual de Contingência do
 * RS);  <br>
 * 9=Contingência off-line da NFC-e (as demais opções de
 * contingência são válidas também para a NFC-e).
 * Para a NFC-e somente estão disponíveis e são válidas as
 * opções de contingência 5 e 9.
 */
public enum ETpEmis {

    NORMAL("1", "Emissão normal (não em contingência)"),
    CONTINGENCIA_FS_IA("2", "Contingência FS-IA, com impressão do DANFE em formulário de segurança"),
    CONTINGENCIA_SCAN("3", "Contingência SCAN (Sistema de Contingência do Ambiente Nacional)"),
    CONTINGENCIA_DPEC("4", "Contingência DPEC (Declaração Prévia da Emissão em Contingência)"),
    CONTINGENCIA_FS_DA("5", "Contingência FS-DA, com impressão do DANFE em formulário de segurança"),
    CONTINGENCIA_SCV_AN("6", "Contingência SVC-AN (SEFAZ Virtual de Contingência do AN)"),
    CONTINGENCIA_SVC_RS("7", "Contingência SVC-RS (SEFAZ Virtual de Contingência do RS)"),
    CONTINGENCIA_OFF_LINE("9", "Contingência off-line da NFC-e (as demais opções de contingência são válidas também para a NFC-e). Para a NFC-e somente estão disponíveis e são válidas as opções de contingência 5 e 9.");

    private String codigo;
    private String descricao;

    ETpEmis(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
