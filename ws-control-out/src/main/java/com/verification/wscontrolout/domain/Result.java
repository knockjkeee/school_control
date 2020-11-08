package com.verification.wscontrolout.domain;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Data
@Entity
@Scope(value = "prototype")
@Table(name = "resultOUT")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "idtransit")
    private Long idTransit;
    @Column(name = "usernametransit")
    private String usernameTransit;
    @Column(name = "clientsgroup")
    private String clientsGroup;
    @Column(name = "turnstileaddr")
    private String turnstileAddr;
    @Column(name = "datatransit")
    private String dataTransit;
    @Column(name = "timetransit")
    private String timeTransit;
    @Column(name = "namelessons",length = 10485760)
    private String nameLessons;
    @Column(name = "personfaceimageurl",length = 10485760)
    private String personFaceImageUrl;
    @Column(name = "result")
    private String result;

//    @Override
//    public String toString() {
//        return "Result{" +
//                "id=" + id +
//                ", idTransit=" + idTransit +
//                ", usernameTransit='" + usernameTransit + '\'' +
//                ", clientsGroup='" + clientsGroup + '\'' +
//                ", turnstileAddr='" + turnstileAddr + '\'' +
//                ", dataTransit='" + dataTransit + '\'' +
//                ", timeTransit='" + timeTransit + '\'' +
//                ", nameLessons='" + nameLessons + '\'' +
//                ", result='" + result + '\'' +
//                '}';
//    }
}
