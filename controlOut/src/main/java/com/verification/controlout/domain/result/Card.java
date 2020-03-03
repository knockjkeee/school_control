package com.verification.controlout.domain.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Card  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cards_id")
    private Cards cards;

    private String lessonid;
    private String classroomids;
    private String period;
    private String weeks;
    private String terms;
    private String days;

    @Override
    public String toString() {
        return "\nCard{" +
                "identifier=" + identifier +
                ", lessonid='" + lessonid + '\'' +
                ", classroomids='" + classroomids + '\'' +
                ", period='" + period + '\'' +
                ", weeks='" + weeks + '\'' +
                ", terms='" + terms + '\'' +
                ", days='" + days + '\'' +
                '}';
    }
}