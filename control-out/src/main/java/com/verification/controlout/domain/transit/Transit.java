package com.verification.controlout.domain.transit;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "enterevents")
public class Transit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idOfEnterEvent; //Bigint
    private String enterName;
    private String turnstileAddr;
    private String passDirection; //int
    private String eventCode; //int
    private String idOfCard; //Bigin
    private String idOfClient; //Bigin
    private String evtDateTime; //TimeStamp

}
