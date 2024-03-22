package com.hackacode.gestionPaqSer.enums;

public enum TipoDeVenta {
    SERVICIO ("Servicio", 0D),
    PAQUETE ("Paquete", 0.1);

    private final String tag;
    private final Double descuento;

    TipoDeVenta(String tag, Double descuento) {
        this.tag = tag;
        this.descuento = descuento;
    }

    public String getTag() {
        return tag;
    }

    public Double getDescuento() {
        return descuento;
    }
}
