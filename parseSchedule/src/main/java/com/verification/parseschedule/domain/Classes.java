package com.verification.parseschedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Classes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;



    @JsonProperty("class")
    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ClassElement> classesClass;
    private String options;
    private String columns;

}