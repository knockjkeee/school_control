package com.verification.controlout.domain.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Sdef implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "daysdefs_id")
    private Daysdefs daysdefs;

    private String id;
    private String name;
    private String sdefShort;
    private String days;
    private String terms;

    @Override
    public String toString() {
        return "\nSdef{" +
                "identifier=" + identifier +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sdefShort='" + sdefShort + '\'' +
                ", days='" + days + '\'' +
                ", terms='" + terms + '\'' +
                '}';
    }
}