package com.verification.match.service;

import com.verification.match.domain.transit.Client;
import com.verification.match.domain.transit.Transit;
import com.verification.match.repo.transit.TransitRepo;
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


    private final SpringWebsocketWithoutStompClientServiceMatch springWebsocketWithoutStompClientServiceMatch;
    private CollegeLookupService collegeLookupService;
    private CollegeLookupServiceClient collegeLookupServiceClient;
    private CompareTwoDataService compareTwoDataService;
    private TransitRepo transitRepo;

    @Autowired
    public AppRunnerCompareDataTwoService(SpringWebsocketWithoutStompClientServiceMatch springWebsocketWithoutStompClientServiceMatch,
                                          CollegeLookupService collegeLookupService, CollegeLookupServiceClient collegeLookupServiceClient,
                                          CompareTwoDataService compareTwoDataService, TransitRepo transitRepo) {
        this.springWebsocketWithoutStompClientServiceMatch = springWebsocketWithoutStompClientServiceMatch;
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
        springWebsocketWithoutStompClientServiceMatch.connect();

        while (true) {

        }
    }
}

