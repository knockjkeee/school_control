package com.verification.parseschedule.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Grades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    @OneToMany(mappedBy = "grades", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Grade> grade;
    private String options;
    private String columns;

}