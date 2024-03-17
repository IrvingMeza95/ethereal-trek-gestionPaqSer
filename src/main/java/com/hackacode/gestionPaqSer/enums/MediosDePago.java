package com.hackacode.gestionPaqSer.enums;

public enum MediosDePago {
    EFECTIVO ("Efectivo", 0),
    TRANSFERENCIA ("Transferencia", 0.024),
    TARJETA_DE_CREDITO ("Tarjeta de crédito", 0.09),
    TARJETA_DE_DEBITO("Tarjeta de debito", 0.03),
    MONEDERO_VIRTUAL("Monedero virtual", 0);

    private final  String tag;
    private final double  comision;

    MediosDePago(String tag, double comision) {
        this.tag = tag;
        this.comision = comision;
    }

    public String getTag() {
        return tag;
    }

    public double getComision() {
        return comision;
    }
}
