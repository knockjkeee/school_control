package com.verification.match.domain.result;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Data
@Entity
@Scope(value = "prototype")
@Table(name = "verification")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idTransit;
    private String usernameTransit;
    private String turnstileAddr;
    private String dataTransit;
    private String timeTransit;
    private Long idVerification;
    private String usernameVerification;
    private String dataVerification;
    private String timeVerification;
    private String detectionFaceImageUrl;
    private String personFaceImageUrl;
    private String result;
}
