package com.example.travelguidewebapplication.enums;

public enum Status {
    ICRA_EDILIB("İCRA EDİLİB"),
    GOZLEMEDE("GÖZLƏMƏDƏ"),
    LEGV_EDILIB("LƏĞV EDİLİB");

    private final String value;

    Status(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
