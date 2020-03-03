package com.verification.controlout.domain.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "class_element")
public class ClassElement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classes_id")
    private Classes classes;

    private String id;
    private String name;
    @JsonProperty("short")
    private String classShort;
    private Teacherid teacherid;
    private String classroomids;
    private String grade;
    private String partnerID;

    @Override
    public String toString() {
        return "\nClassElement{" +
                "identifier=" + identifier +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", classShort='" + classShort + '\'' +
                ", teacherid=" + teacherid +
                ", classroomids='" + classroomids + '\'' +
                ", grade='" + grade + '\'' +
                ", partnerID='" + partnerID + '\'' +
                '}';
    }
}