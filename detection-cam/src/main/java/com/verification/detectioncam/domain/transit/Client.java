package com.verification.detectioncam.domain.transit;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idOfClient; //Bigint
    private String clientsGroupId;
    private String surName;
    private String name;
    private String SecondName;

    @Lob
    private byte[] Image;

}
