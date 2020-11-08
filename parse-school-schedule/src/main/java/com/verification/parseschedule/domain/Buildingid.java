package com.verification.parseschedule.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import java.io.IOException;


public enum Buildingid {
    FFFFFFFFA3_B3342_C, FFFFFFFFB82517_C1;

    @JsonValue
    public String toValue() {
        switch (this) {
            case FFFFFFFFA3_B3342_C: return "FFFFFFFFA3B3342C";
            case FFFFFFFFB82517_C1: return "FFFFFFFFB82517C1";
        }
        return null;
    }

    @JsonCreator
    public static Buildingid forValue(String value) throws IOException {
        if (value.equals("FFFFFFFFA3B3342C")) return FFFFFFFFA3_B3342_C;
        if (value.equals("FFFFFFFFB82517C1")) return FFFFFFFFB82517_C1;
        throw new IOException("Cannot deserialize Buildingid");
    }
}
