package com.verification.controlout.domain.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teachers_id")
    private Teachers teachers;

    private String id;
    private String firstname;
    private String lastname;
    private String name;
    private String teacherShort;
    private Gender gender;
    private String color;
    private String email;
    private String mobile;
    private String partnerID;

    @Override
    public String toString() {
        return "\nTeacher{" +
                "identifier=" + identifier +
                ", id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", teacherShort='" + teacherShort + '\'' +
                ", gender=" + gender +
                ", color='" + color + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", partnerID='" + partnerID + '\'' +
                '}';
    }
}
