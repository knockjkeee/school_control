package com.verification.detectioncam.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verification.detectioncam.domain.iss.Detection;
import com.verification.detectioncam.domain.iss.Subscribe;
import com.verification.detectioncam.domain.result.Result;
import com.verification.detectioncam.domain.transit.Client;
import com.verification.detectioncam.domain.transit.ClientsPhoto;
import com.verification.detectioncam.domain.transit.Transit;
import com.verification.detectioncam.repo.result.ResultRepo;
import com.verification.detectioncam.util.CompareUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
@Scope(value = "prototype")
public class ISSLookupService {

    private static final Logger logger = LoggerFactory.getLogger(ISSLookupService.class);
    private static final DateTimeFormatter formatData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final RestTemplate restTemplate;
    private CompareTwoDataService compareTwoDataService;
    private CollegeLookupService collegeLookupService;
    private CollegeLookupServiceClient collegeLookupServiceClient;
    private ObjectMapper objectMapper;
    private ResultRepo resultRepo;


    @Autowired
    public ISSLookupService(RestTemplateBuilder restTemplateBuilder,
                            CompareTwoDataService compareTwoDataService,
                            CollegeLookupService collegeLookupService,
                            CollegeLookupServiceClient collegeLookupServiceClient, ObjectMapper objectMapper, ResultRepo resultRepo) {
        this.restTemplate = restTemplateBuilder.build();
        this.compareTwoDataService = compareTwoDataService;
        this.collegeLookupService = collegeLookupService;
        this.collegeLookupServiceClient = collegeLookupServiceClient;
        this.objectMapper = objectMapper;
        this.resultRepo = resultRepo;
    }

    public void checkMainLinkDetection(Subscribe subscribe) {
        if (subscribe.getAction() != null) {
            logger.info(">>>>>>Start connect ws ISS Detection event");
        } else {
            logger.info("****************<Detection>****************");

            Detection detection = subscribe.getDetection();
            logger.info("<Detection> <Detection>: " + detection);

            String timestampISS = detection.getTrackFinishTimestamp();
            LocalDateTime dateTimeISS = LocalDateTime.parse(timestampISS.substring(0, timestampISS.length() - 5)).plusHours(3);
            Transit transitResult = collegeLookupService.findTransitWhereImageNull();
            logger.info("<Detection> <transitResult>: " + transitResult);

            String newCurrentTimeTransit = CompareUtils.newCurrentTimeTransit(transitResult);
            LocalDateTime dateTimeTransit = LocalDateTime.parse(newCurrentTimeTransit);
            long diffTime = Math.abs(ChronoUnit.SECONDS.between(dateTimeISS, dateTimeTransit));
            logger.info("checkMainLinkDetection dateTimeTransit: " + dateTimeTransit);
            logger.info("checkMainLinkDetection dateTimeISS: " + dateTimeISS);

            logger.info("tempY!!!! ->>>>: " + Double.parseDouble(detection.getBounding_box().get("y")));
            logger.info("tempH!!!! ->>>>: " + Double.parseDouble(detection.getBounding_box().get("h")));

            if (!(Double.parseDouble(detection.getBounding_box().get("y")) > 0.82) &&
                    !(Double.parseDouble(detection.getBounding_box().get("h")) > 0.21766)) {

                if (diffTime <= 3) {

                    //TODO transit entity private String idOfClient; //Bigin

                    //TODO
                    try {
                        Thread.sleep(2000); //TODO 9 sec
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Result temp = resultRepo.findFirstByOrderByIdDesc();
                    logger.error("Temp vs temp match: " + temp);
                    logger.error("transitResult.getIdOfClient()" + transitResult.getIdOfClient());
                    String tt = String.valueOf(temp.getIdTransit());
                    logger.error("String.valueOf(temp.getIdTransit())" + tt);

                    if (transitResult.getIdOfClient() != null) {
                        if (!transitResult.getIdOfClient().equals(String.valueOf(temp.getIdTransit()))) {
                            ClientsPhoto photo = collegeLookupServiceClient.findTransitPersonalClientPhoto(transitResult.getIdOfClient());
                            logger.error("<<PHOTO>>: --->> " + photo);
                            Client client = collegeLookupServiceClient.findTransitPersonalClient(transitResult.getIdOfClient());
                            if (photo == null) {
                                if (client.getImage() == null) {
                                    logger.error("Transit vs Client check == null image");
                                    compareTwoDataService.createResultWhereImageNull(transitResult, client, detection, "Нет данных");
                                } else {
                                    logger.error("Transit vs Client check != null image");
                                    compareTwoDataService.createResultWhereImageNull(transitResult, client, detection, "Инцидент");
                                }
                            } else {
                                if (client.getImage() == null) {
                                    logger.error("Transit vs Client check == null image");
                                    client.setImage(photo.getImageBytes());
                                    compareTwoDataService.createResultWhereImageNull(transitResult, client, detection, "Нет данных+ФОТО");
                                } else {
                                    logger.error("Transit vs Client check != null image");
                                    client.setImage(photo.getImageBytes());
                                    compareTwoDataService.createResultWhereImageNull(transitResult, client, detection, "Инцидент+ФОТО");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
