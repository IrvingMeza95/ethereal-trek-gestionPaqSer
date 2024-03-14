package com.hackacode.gestionPaqSer.enums;

public enum Cargo {
    VENDEDOR ("Vendedor");

    private final String tag;

    Cargo(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

}
