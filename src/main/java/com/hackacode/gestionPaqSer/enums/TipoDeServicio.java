package com.hackacode.gestionPaqSer.enums;

public enum TipoDeServicio {
    HOTEL_POR_NOCHE ("Hotel por noche/s"),
    ALQUILER_DE_AUTO ("Alguiler de auto"),
    PASAJES_DE_COLECTIVO ("Pasajes de colectivo"),
    PASAJES_DE_AVION ("Pasajes de avión"),
    PASAJES_DE_TREN ("Pasajes de tren"),
    EXCURSIONES ("Excursiones"),
    ENTRADAS_A_EVENTOS ("Entradas a eventos");

    private final String tag;

    TipoDeServicio(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
