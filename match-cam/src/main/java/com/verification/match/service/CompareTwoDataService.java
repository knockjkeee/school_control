package com.verification.match.service;


import com.verification.match.domain.iss.Detection;
import com.verification.match.domain.iss.Verification;
import com.verification.match.domain.result.Result;
import com.verification.match.domain.transit.Client;
import com.verification.match.domain.transit.Transit;
import com.verification.match.repo.result.ResultRepo;
import com.verification.match.util.CompareUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
@Scope(value = "prototype")
public class CompareTwoDataService {
    private static final Logger logger = LoggerFactory.getLogger(CompareTwoDataService.class);
    private static final String IMAGE = "https://img2.freepng.ru/20180615/rtc/kisspng-avatar-user-profile-male-logo-profile-icon-5b238cb002ed52.870627731529056432012.jpg";
    private static final DateTimeFormatter formatterYears = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");


    private SaveCompareTwoDataService saveCompareTwoDataService;
    private ResultRepo resultRepo;

    @Autowired
    public CompareTwoDataService(SaveCompareTwoDataService saveCompareTwoDataService, ResultRepo resultRepo) {
        this.saveCompareTwoDataService = saveCompareTwoDataService;
        this.resultRepo = resultRepo;
    }


    public void compareTwoData(Verification verification, Client client, Transit transit) {
        Result temp = new Result();

        //ISS time
        String timestampISS = verification.getTimestampISS();
        String currentTimeISS = timestampISS.substring(0, timestampISS.length() - 5);
        LocalDateTime dateTimeISS = LocalDateTime.parse(currentTimeISS).plusHours(3);
        String timeStampYearsISS = dateTimeISS.format(formatterYears);
        String timeStampTimeISS = dateTimeISS.format(formatterTime);

        //Transit time
        String newCurrentTimeTransit = CompareUtils.newCurrentTimeTransit(transit);
        LocalDateTime dateTimeTransit = LocalDateTime.parse(newCurrentTimeTransit);
        String timeStampYearsTransit = CompareUtils.getDataFormatTransitYear(transit, formatterYears);
        String timeStampTimeTransit = CompareUtils.getDataFormatTransitTime(transit, formatterTime);

        long diffDataTime = Math.abs(ChronoUnit.SECONDS.between(dateTimeISS, dateTimeTransit));
        logger.error("dateTimeTransit: " + dateTimeTransit);
        logger.error("dateTimeISS: " + dateTimeISS);

        if (diffDataTime <= 15) {
            if (verification.getLastNameISS().equals("Нет данных") || verification.getLastNameISS().equals("Инцидент")) {
                createResultData(transit, verification, client, temp, timeStampYearsISS,
                        timeStampTimeISS, timeStampYearsTransit, timeStampTimeTransit, "Инцидент");
            } else {
                createResultData(transit, verification, client, temp, timeStampYearsISS,
                        timeStampTimeISS, timeStampYearsTransit, timeStampTimeTransit, "Ok");

                String name = client.getSurName() + " " + client.getName() + " " + client.getSecondName();
                Result transitOrderById = resultRepo.findFirstByUsernameTransitAndDataTransitOrderById(name, timeStampYearsTransit);
                if (transitOrderById != null) {
                    if (transitOrderById.getResult().equals("Инцидент")) {
                        resultRepo.delete(transitOrderById);
                    }
                }

            }

            String encode = Base64.getEncoder().encodeToString(client.getImage());
            String encodeImage = "data:image/jpeg;base64," + encode;
            temp.setDetectionFaceImageUrl(verification.getDetectionFaceImageUrl());
            temp.setPersonFaceImageUrl(encodeImage);
            loadCompareData(temp);
        }


    }

    private void createResultData(Transit transit, Verification verification, Client client, Result temp,
                                  String timeStampYearsISS, String timeStampTimeISS, String timeStampYearsTransit,
                                  String timeStampTimeTransit, String result) {
        String name = client.getSurName() + " " + client.getName() + " " + client.getSecondName();

        if (transit.getEnterName().equals("Охрана")) {
            temp.setIdTransit(transit.getIdOfEnterEvent());
            temp.setResult("112");
        } else {
            temp.setIdTransit(Long.valueOf(transit.getIdOfClient()));
            temp.setResult(result);
        }
        temp.setTurnstileAddr(transit.getTurnstileAddr());
        temp.setUsernameTransit(name);
        temp.setDataTransit(timeStampYearsTransit);
        temp.setTimeTransit(timeStampTimeTransit);
        temp.setIdVerification(Long.valueOf(verification.getPersonIdISS()));
        temp.setUsernameVerification(verification.getLastNameISS());
        temp.setDataVerification(timeStampYearsISS);
        temp.setTimeVerification(timeStampTimeISS);

    }


    public void createResultWhereImageNull(Transit transit, Client client, Detection detection, String state) {
        String timeStampYearsTransit = CompareUtils.getDataFormatTransitYear(transit, formatterYears);
        String timeStampTimeTransit = CompareUtils.getDataFormatTransitTime(transit, formatterTime);
        Result temp = new Result();
        temp.setIdTransit(Long.valueOf(transit.getIdOfClient()));
        String name = client.getSurName() + " " + client.getName() + " " + client.getSecondName();
        temp.setUsernameTransit(name);
        temp.setDataTransit(timeStampYearsTransit);
        temp.setTimeTransit(timeStampTimeTransit);
        temp.setTurnstileAddr(transit.getTurnstileAddr());
        temp.setIdVerification(404L);
        temp.setUsernameVerification(state);
        temp.setDetectionFaceImageUrl(detection.getLinks().get("detection_image"));
        if (client.getImage() != null) {
            String encode = Base64.getEncoder().encodeToString(client.getImage());
            String encodeImage = "data:image/jpeg;base64," + encode;
            temp.setPersonFaceImageUrl(encodeImage);
        } else {
            temp.setPersonFaceImageUrl(IMAGE);
        }
        temp.setResult("Нет данных");
        loadCompareData(temp);

    }

    private void loadCompareData(Result temp) {
        saveCompareTwoDataService.saveData(temp);
    }
}
