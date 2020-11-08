package com.verification.detectioncam.domain.transit;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "clients_photo")
public class ClientsPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idOfRecord;
    private String idOfClient; //Bigint
    @Lob
    private byte[] ImageBytes;
}
