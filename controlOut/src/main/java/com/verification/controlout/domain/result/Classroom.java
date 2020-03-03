package com.verification.controlout.domain.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Classroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classrooms_id")
    private Classrooms classrooms;

    private String id;
    private String name;
    private String classroomShort;
    private String capacity;
    private Buildingid buildingid;
    private String partnerID;

    @Override
    public String toString() {
        return "\nClassroom{" +
                "identifier=" + identifier +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", classroomShort='" + classroomShort + '\'' +
                ", capacity='" + capacity + '\'' +
                ", partnerID='" + partnerID + '\'' +
                '}';
    }
}
