package com.verification.parseschedule.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

public enum Gender {
    F;

    @JsonValue
    public String toValue() {
        switch (this) {
            case F: return "F";
        }
        return null;
    }

    @JsonCreator
    public static Gender forValue(String value) throws IOException {
        if (value.equals("F")) return F;
        throw new IOException("Cannot deserialize Gender");
    }
}