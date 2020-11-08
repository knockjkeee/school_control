package com.verification.controlout.domain.transit;

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

    @Override
    public String toString() {
        return "Client{" +
                "idOfClient='" + idOfClient + '\'' +
                ", clientsGroupId='" + clientsGroupId + '\'' +
                ", surName='" + surName + '\'' +
                ", name='" + name + '\'' +
                ", SecondName='" + SecondName + '\'' +
                '}';
    }
}
