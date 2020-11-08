package com.verification.controlout.domain.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

public enum Teacherid {
    EMPTY, FFFFFFFFE97_A9812, THE_000000005_E1_FEBCF;

    @JsonValue
    public String toValue() {
        switch (this) {
            case EMPTY: return "";
            case FFFFFFFFE97_A9812: return "FFFFFFFFE97A9812";
            case THE_000000005_E1_FEBCF: return "000000005E1FEBCF";
        }
        return null;
    }

    @JsonCreator
    public static Teacherid forValue(String value) throws IOException {
        if (value.equals("")) return EMPTY;
        if (value.equals("FFFFFFFFE97A9812")) return FFFFFFFFE97_A9812;
        if (value.equals("000000005E1FEBCF")) return THE_000000005_E1_FEBCF;
        throw new IOException("Cannot deserialize Teacherid");
    }
}

