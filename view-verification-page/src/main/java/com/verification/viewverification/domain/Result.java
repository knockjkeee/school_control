package com.verification.viewverification.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Data
@Entity
//@Scope(value = "prototype")
@Table(name = "verification")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "idtransit")
    private Long idTransit;
    @Column(name = "usernametransit")
    private String usernameTransit;
    @Column(name = "turnstileaddr")
    private String turnstileAddr;
    @Column(name = "datatransit")
    private String dataTransit;
    @Column(name = "timetransit")
    private String timeTransit;
    @Column(name = "idverification")
    private Long idVerification;
    @Column(name = "usernameverification")
    private String usernameVerification;
    @Column(name = "dataverification")
    private String dataVerification;
    @Column(name = "timeverification")
    private String timeVerification;
    @Column(name = "detectionfaceimageurl")
    private String detectionFaceImageUrl;
    @Column(name = "personfaceimageurl")
    private String personFaceImageUrl;
    @Column(name = "result")
    private String result;
}
