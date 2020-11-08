package com.verification.controlout.domain.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Classrooms implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    @OneToMany(mappedBy = "classrooms", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Classroom> classroom;
    private String options;
    private String columns;

}