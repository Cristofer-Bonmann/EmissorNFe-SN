package com.vertyce.enums;

public enum EICMSTotMethod {
    VPROD("VProd"),
    VFRETE("VFrete"),
    VSEG("VSeg"),
    VDESC("VDesc"),

    VOUTRO("VOutro");

    private String nomeMethod;

    EICMSTotMethod(String nomeMethod){
        this.nomeMethod = nomeMethod;
    }

    public String getNomeMethod(){
        return this.nomeMethod;
    }
}
