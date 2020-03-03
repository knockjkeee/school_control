package com.verification.match.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verification.match.domain.iss.*;
import com.verification.match.domain.transit.Client;
import com.verification.match.domain.transit.ClientsPhoto;
import com.verification.match.domain.transit.Transit;
import com.verification.match.repo.result.ResultRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

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


    public void checkMainLinkMatch(Subscribe subscribe) throws IOException {
        if (subscribe.getAction() != null) {
            logger.info(">>>>>>Start connect ws ISS Match event");
        } else {
            logger.info("****************<Match>****************");
            Match link = subscribe.getMatch();
            Match matchTemp = subscribe.getMatch();
            Match mathResult = subscribe.getMatch();

            double tempY = Double.parseDouble(mathResult.getBounding_box().get("y"));
            double tempH = Double.parseDouble(mathResult.getBounding_box().get("h"));
            logger.info("tempY!!!! ->>>>: " + tempY);

            if (!(tempY > 0.82) && !(tempH > 0.21766)) {
                System.out.println("Work!");

                Person personResult = mathResult.getPerson();
                Detection detection = mathResult.getDetection();
                MatchPersonalImage machedImage = mathResult.getMatchedPersonFaceImage();

                logger.info("PERSON!!!! ->>>>: " + personResult);
                logger.info("DETECTION!!!! ->>>>: " + detection);

                //TODO add sleep 1 or 0.5 second a tempt //
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Transit firstTransitResult = collegeLookupService.findTransit("00250B00E0F9");
                Transit secondTransitResult = collegeLookupService.findTransit("00250B00E0F6");

                
                Client client = collegeLookupServiceClient.findTransitPersonalClient(Objects.requireNonNull(personResult).getLast_name());
                ClientsPhoto photo = collegeLookupServiceClient.findTransitPersonalClientPhoto(client.getIdOfClient());
                if (photo != null) {
                    client.setImage(photo.getImageBytes());
                }

                logger.info("FIRST TRANSIT!!!! ->>>>: " + firstTransitResult);

                String timestampTransit = firstTransitResult.getEvtDateTime();
                String currentTimeTransit = timestampTransit.substring(0, timestampTransit.length() - 2);
                String newTime = currentTimeTransit.replaceAll(" ", "T");
                LocalDateTime dateTimeTransit = LocalDateTime.parse(newTime).minusSeconds(10); // approve TODO minus 10 sec
                String customDataTime = dateTimeTransit.format(formatData);
                logger.info("new custom time!!!! ->>>> " + customDataTime);
                logger.info("firstTransitResult time ->>>> " + firstTransitResult.getEvtDateTime());

                List<Transit> listMainTransitResult = collegeLookupService.findCustomTransit(customDataTime, firstTransitResult.getEvtDateTime(), "00250B00E0F9");
                List<Transit> listSecondTransitResult = collegeLookupService.findCustomTransit(customDataTime, secondTransitResult.getEvtDateTime(), "00250B00E0F6");

                Transit transitResult;
                Transit transitTempAnotherTurnstileAddr;
                //approve TODO add temp transit with custom time and add view new field #TurnAdress#

                logger.error("listMainTransitResult.size: " + listMainTransitResult.size());
                logger.error(String.valueOf(listMainTransitResult));
                logger.error("listSecondTransitResult.size: " + listSecondTransitResult.size());
                logger.error(String.valueOf(listSecondTransitResult));

                transitResult = getTransit(firstTransitResult, listMainTransitResult, personResult);
                transitTempAnotherTurnstileAddr = getTransit(secondTransitResult, listSecondTransitResult, personResult);
                logger.info("TRANSIT RESULT!!!! ->>>>" + transitResult);
                logger.info("TRANSIT TEMP RESULT!!!! ->>>>" + transitTempAnotherTurnstileAddr);

                Verification verification;

                if (personResult.getLast_name().equals(transitTempAnotherTurnstileAddr.getIdOfClient())) {
                    verification = compareID(transitTempAnotherTurnstileAddr, personResult, mathResult, detection, machedImage);
                    compareTwoDataService.compareTwoData(verification, client, transitTempAnotherTurnstileAddr);
                } else {
                    if (transitResult.getEventCode().equals("112")) {
                        logger.info("!!!!!!!!!!!!!!!!112!!!!!!!!!!!!!!!!");
                        Transit badTransit = new Transit();
                        badTransit.setIdOfEnterEvent(112L);
                        badTransit.setEnterName("Охрана");
                        badTransit.setTurnstileAddr("00250B00E0F9");
                        badTransit.setPassDirection("0");
                        badTransit.setEventCode("112");
                        badTransit.setIdOfCard("Охрана");
                        badTransit.setIdOfClient("Охрана");
                        badTransit.setEvtDateTime(transitResult.getEvtDateTime());
                        verification = compareID(badTransit, personResult, mathResult, detection, machedImage);
                        compareTwoDataService.compareTwoData(verification, client, badTransit);
                    } else {

                        verification = compareID(transitResult, personResult, mathResult, detection, machedImage);
                        compareTwoDataService.compareTwoData(verification, client, transitResult);
                    }
                }
            }
        }
    }

    private Transit getTransit(Transit transit, List<Transit> listTransit, Person person) {
        Transit result;
        if (listTransit.size() == 1) {
            result = listTransit.get(0);
        } else if (listTransit.size() == 2) {
            if (listTransit.get(listTransit.size() - 1).getIdOfClient() != null &&
                    transit.getIdOfClient() != null) {
                if (listTransit.get(listTransit.size() - 1).getIdOfClient().equals(person.getLast_name())) {
                    result = listTransit.get(listTransit.size() - 1);
                } else if (listTransit.get(listTransit.size() - 2).getIdOfClient() != null && listTransit.get(listTransit.size() - 2).getIdOfClient().equals(person.getLast_name())) {
                    result = listTransit.get(listTransit.size() - 2);
                } else {
                    result = listTransit.get(listTransit.size() - 1);
                }
            } else {
                result = getTransitInner(transit, listTransit);
            }
        } else if (listTransit.size() >= 3) {
            if (listTransit.get(listTransit.size() - 1).getIdOfClient() != null &&
                    transit.getIdOfClient() != null) {
                if (listTransit.get(listTransit.size() - 1).getIdOfClient().equals(person.getLast_name())) {
                    result = listTransit.get(listTransit.size() - 1);
                } else if (listTransit.get(listTransit.size() - 2).getIdOfClient() != null && listTransit.get(listTransit.size() - 2).getIdOfClient().equals(person.getLast_name())) {
                    result = listTransit.get(listTransit.size() - 2);
                } else if (listTransit.get(listTransit.size() - 3).getIdOfClient() != null && listTransit.get(listTransit.size() - 3).getIdOfClient().equals(person.getLast_name())) {
                    result = listTransit.get(listTransit.size() - 3);
                } else {
                    result = listTransit.get(listTransit.size() - 1);
                }
            } else {
                result = getTransitInner(transit, listTransit);
            }

        } else {
            result = transit;
        }
        return result;
    }

    private Transit getTransitInner(Transit transit, List<Transit> listTransit) {
        Transit result;
        if (listTransit.get(listTransit.size() - 1).getIdOfClient() == null &&
                transit.getIdOfClient() == null) {
            result = listTransit.get(listTransit.size() - 1);
        } else {
            result = listTransit.get(listTransit.size() - 1);
        }
        return result;
    }

    private Verification compareID(Transit transitResult, Person personResult, Match mathResult, Detection detection, MatchPersonalImage mImage) {
        Verification verification;
        if (personResult.getLast_name() != null &&
                transitResult.getIdOfClient().equals(personResult.getLast_name())) {
            verification = loadVerificationDataNotNull(mathResult, personResult.getLast_name(), detection, mImage);
        } else if (personResult.getLast_name() == null) {
            verification = loadVerificationDataNotNull(mathResult, "Нет данных", detection, mImage);
        } else {
            verification = loadVerificationDataNotNull(mathResult, "Инцидент", detection, mImage);
        }
        return verification;
    }

    private Verification loadVerificationDataNotNull(Match mathResult, String name, Detection detection, MatchPersonalImage mImage) {
        Verification verification = new Verification();
        verification.setSimilarityISS(mathResult.getSimilarity());
        verification.setTimestampISS(detection.getTrackFinishTimestamp());
        verification.setPersonIdISS(mathResult.getId());
        verification.setLastNameISS(name);
        verification.setDetectionFaceImageUrl(detection.getLinks().get("detection_image"));
        verification.setPersonFaceImageUrl(mImage.getLinks().get("source"));
        return verification;
    }
}
