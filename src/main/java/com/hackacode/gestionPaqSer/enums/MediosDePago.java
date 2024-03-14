package com.hackacode.gestionPaqSer.enums;

public enum MediosDePago {
    EFECTIVO ("Efectivo"),
    TRANSFERENCIA ("Transferencia"),
    CHEQUE ("Cheque");

    private final  String tag;

    MediosDePago(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

}
