package com.verification.controlout.domain.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Lessons implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    @OneToMany(mappedBy = "lessons", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lesson> lesson;
    private String options;
    private String columns;
}
