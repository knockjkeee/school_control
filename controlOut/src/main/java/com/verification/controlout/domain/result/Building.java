package com.verification.controlout.domain.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Building implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "weeksdefs_id")
    private Weeksdefs weeksdefs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjects_id")
    private Subjects subjects;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buildings_id")
    private Buildings buildings;

    private String id;
    private String name;
    private String partnerID;
    private String buildingShort;
    private String weeks;

    @Override
    public String toString() {
        return "\nBuilding{" +
                "identifier=" + identifier +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", partnerID='" + partnerID + '\'' +
                ", buildingShort='" + buildingShort + '\'' +
                ", weeks='" + weeks + '\'' +
                '}';
    }
}
