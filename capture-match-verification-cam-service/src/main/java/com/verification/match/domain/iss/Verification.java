package com.verification.match.domain.iss;


import lombok.Data;
import org.springframework.context.annotation.Scope;

@Data
@Scope(value = "prototype")
public class Verification {
    private String similarityISS; //0,91%
    private String timestampISS; //time data
    private String personIdISS;  //idiss
    private String lastNameISS; //id скуд
    private String detectionFaceImageUrl;
    private String personFaceImageUrl;


}

