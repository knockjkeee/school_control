package com.verification.controlout.domain.result;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.type.BlobType;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.sql.Blob;

@Data
@Entity
@Scope(value = "prototype")
@Table(name = "resultOUT")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idTransit;//todo
    private String usernameTransit;//todo
    private String clientsGroup;//todo
    private String turnstileAddr;//todo
    private String dataTransit;//todo
    private String timeTransit;//todo

    //    private Long idVerification;

    @Column(length = 10485760)
    private String nameLessons;
//    private String timeLessonPeriod;
    //    private String detectionFaceImageUrl;


    @Column(length = 10485760)
    private String personFaceImageUrl;

//    @Lob
//    private byte[] Image;

    private String result;

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", idTransit=" + idTransit +
                ", usernameTransit='" + usernameTransit + '\'' +
                ", clientsGroup='" + clientsGroup + '\'' +
                ", turnstileAddr='" + turnstileAddr + '\'' +
                ", dataTransit='" + dataTransit + '\'' +
                ", timeTransit='" + timeTransit + '\'' +
                ", nameLessons='" + nameLessons + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
