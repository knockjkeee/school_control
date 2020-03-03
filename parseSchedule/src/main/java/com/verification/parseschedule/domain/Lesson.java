package com.verification.parseschedule.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lessons_id")
    private Lessons lessons;

    @Column(length = 1024)
    private String classroomids;
    private String weeksdefid;
    private String daysdefid;
    private String classids;
    private String teacherids;
    private String seminargroup;
    private String subjectid;
    private String capacity;
    private String periodsperweek;
    private String partner_id;
    private String groupids;
    private String termsdefid;
    private String periodspercard;

    @Override
    public String toString() {
        return "\nLesson{" +
                "identifier=" + identifier +
                ", id='" + id + '\'' +
                ", classroomids='" + classroomids + '\'' +
                ", weeksdefid='" + weeksdefid + '\'' +
                ", daysdefid='" + daysdefid + '\'' +
                ", classids='" + classids + '\'' +
                ", teacherids='" + teacherids + '\'' +
                ", seminargroup='" + seminargroup + '\'' +
                ", subjectid='" + subjectid + '\'' +
                ", capacity='" + capacity + '\'' +
                ", periodsperweek='" + periodsperweek + '\'' +
                ", partner_id='" + partner_id + '\'' +
                ", groupids='" + groupids + '\'' +
                ", termsdefid='" + termsdefid + '\'' +
                ", periodspercard='" + periodspercard + '\'' +
                '}';
    }
}
