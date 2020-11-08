package com.verification.parseschedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grades_id")
    private Grades grades;

    private String name;
    private String gradeShort;
    private String grade;

    @Override
    public String toString() {
        return "\nGrade{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", gradeShort='" + gradeShort + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}