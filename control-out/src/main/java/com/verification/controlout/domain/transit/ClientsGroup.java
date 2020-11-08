package com.verification.controlout.domain.transit;


import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Data
@Entity
//@Scope(value = "prototype")
@Table(name = "clients_groups")
public class ClientsGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idOfClientsGroup;
    private String name;
    private String disablePlanCreation;
    private String version;
    private String syncState;
    private String createdDate;
    private String lastUpdate;
    private String groupType;


}

