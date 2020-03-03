package com.verification.parseschedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "group_main")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groups_id")
    private Groups groups;

    private String id;
    private String name;
    private String classid;
    private String studentids;
    private String entireclass;
    private String divisiontag;
    private String studentcount;

    @Override
    public String toString() {
        return "\nGroup{" +
                "identifier=" + identifier +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", classid='" + classid + '\'' +
                ", studentids='" + studentids + '\'' +
                ", entireclass='" + entireclass + '\'' +
                ", divisiontag='" + divisiontag + '\'' +
                ", studentcount='" + studentcount + '\'' +
                '}';
    }
}