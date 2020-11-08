package com.verification.controlout.service;

import com.verification.controlout.domain.result.Result;
import com.verification.controlout.domain.transit.Client;
import com.verification.controlout.domain.transit.ClientsGroup;
import com.verification.controlout.domain.transit.ClientsPhoto;
import com.verification.controlout.domain.transit.Transit;
import com.verification.controlout.repo.timerable.ResultRepo;
import com.verification.controlout.util.CompareUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

@Service
@Scope(value = "prototype")
public class SaveData {
    private static final Logger logger = LoggerFactory.getLogger(SaveData.class);
    private static final String IMAGE = "https://img2.freepng.ru/20180615/rtc/kisspng-avatar-user-profile-male-logo-profile-icon-5b238cb002ed52.870627731529056432012.jpg";
    private static final DateTimeFormatter formatterYears = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");

    private ResultRepo resultRepo;

    public SaveData(ResultRepo resultRepo) {
        this.resultRepo = resultRepo;
    }

    //Todo create new Repo and Domain
    public void save(Transit transit, Client client, ClientsPhoto clientsPhoto, ClientsGroup clientsGroup, String result, List<String> lessons) {
        String timeStampYearsTransit = CompareUtils.getDataFormatTransitYear(transit, formatterYears);
        String timeStampTimeTransit = CompareUtils.getDataFormatTransitTime(transit, formatterTime);
        Result saveResult = new Result();
        String name = client.getSurName() + " " + client.getName() + " " + client.getSecondName();

        //todo save data
        String nameClass = clientsGroup.getName();
//        saveResult.setClientsGroup(nameClass.replaceAll("-", "").toUpperCase());
        saveResult.setClientsGroup(nameClass);

        saveResult.setIdTransit(Long.valueOf(transit.getIdOfClient()));
        saveResult.setUsernameTransit(name);
        saveResult.setTurnstileAddr(transit.getTurnstileAddr());
        saveResult.setDataTransit(timeStampYearsTransit);
        saveResult.setTimeTransit(timeStampTimeTransit);

        if (lessons.size() >= 1) {
            StringBuilder builder = new StringBuilder();
            for (String lesson : lessons) {
                builder.append(lesson).append("\n");
            }
            saveResult.setNameLessons(builder.toString());

        } else {
            saveResult.setNameLessons("информация отсутствует");
        }

        if (clientsPhoto == null) {
            if (client.getImage() == null) {
                saveResult.setPersonFaceImageUrl(IMAGE);
            } else {
                String encode = Base64.getEncoder().encodeToString(client.getImage());
                saveResult.setPersonFaceImageUrl("data:image/jpeg;base64," + encode);
            }
        } else {
            String encode = Base64.getEncoder().encodeToString(clientsPhoto.getImageBytes());
            saveResult.setPersonFaceImageUrl("data:image/jpeg;base64," + encode);
        }
        saveResult.setResult(result);
        logger.error(String.valueOf(saveResult));
        resultRepo.save(saveResult);
    }
}



