package com.example.travelguidewebapplication.enums;

public enum Status {
    COMPLETED("İCRA EDİLİB"),
    PENDING("GÖZLƏMƏDƏ"),
    APPROVED("LƏĞV EDİLİB");

    private final String value;

    Status(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (Status status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Geçersiz Status değeri: " + value);
    }
}
