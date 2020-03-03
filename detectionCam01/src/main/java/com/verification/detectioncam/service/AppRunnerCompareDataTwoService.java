package com.verification.detectioncam.service;

import com.verification.detectioncam.domain.transit.Client;
import com.verification.detectioncam.domain.transit.Transit;
import com.verification.detectioncam.repo.transit.TransitRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class AppRunnerCompareDataTwoService implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppRunnerCompareDataTwoService.class);
    private static final DateTimeFormatter formatData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    private final SpringWebsocketWithoutStompClientServiceDetection springWebsocketWithoutStompClientServiceDetection;
    private CollegeLookupService collegeLookupService;
    private CollegeLookupServiceClient collegeLookupServiceClient;
    private CompareTwoDataService compareTwoDataService;
    private TransitRepo transitRepo;

    @Autowired
    public AppRunnerCompareDataTwoService(SpringWebsocketWithoutStompClientServiceDetection springWebsocketWithoutStompClientServiceDetection,
                                          CollegeLookupService collegeLookupService, CollegeLookupServiceClient collegeLookupServiceClient,
                                          CompareTwoDataService compareTwoDataService, TransitRepo transitRepo) {
        this.springWebsocketWithoutStompClientServiceDetection = springWebsocketWithoutStompClientServiceDetection;
        this.collegeLookupService = collegeLookupService;
        this.collegeLookupServiceClient = collegeLookupServiceClient;
        this.compareTwoDataService = compareTwoDataService;
        this.transitRepo = transitRepo;
    }


    @Override
    public void run(String... args) throws Exception {
        Transit transitResult;
        Client clientCheck = new Client();
        Thread.sleep(2000);
        springWebsocketWithoutStompClientServiceDetection.connect();

        while (true) {

        }
    }
}

