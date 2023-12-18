package com.example.investotrack.webapp.validation;

public enum KnownApiVersion {
    V1(1);

    private final double version;

    KnownApiVersion(double version) {
        this.version = version;
    }

    public double getVersion() {
        return version;
    }
}
