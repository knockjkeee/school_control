package com.verification.parseschedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "timetable")
public class Timetable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;

//    @Transactional

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "period_ref")
    private Periods periods;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Daysdefs daysdefs;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Weeksdefs weeksdefs;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Termsdefs termsdefs;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Subjects subjects;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teachers teachers;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Buildings buildings;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Classrooms classrooms;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Grades grades;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Classes classes;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Groups groups;
    private Students students;
    private Students studentsubjects;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Lessons lessons;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cards cards;
    private String ascttversion;
    private String importtype;
    private String options;
    private String defaultexport;
    private String displayname;
    private String displaycountries;


}
