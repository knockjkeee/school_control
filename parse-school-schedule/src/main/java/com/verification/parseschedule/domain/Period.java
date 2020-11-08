package com.verification.parseschedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Period  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identifier;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "periods_id")
    private Periods periods;

    private String name;
    private String periodShort;
    private String period;
    private String starttime;
    private String endtime;

    @Override
    public String toString() {
        return "\nPeriod{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", periodShort='" + periodShort + '\'' +
                ", period='" + period + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }
}