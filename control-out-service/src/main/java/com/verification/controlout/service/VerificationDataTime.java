package com.verification.controlout.service;


import com.verification.controlout.domain.Status;
import com.verification.controlout.domain.transit.Transit;
import com.verification.controlout.repo.transit.TransitRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VerificationDataTime {
    private static final Logger logger = LoggerFactory.getLogger(VerificationDataTime.class);

    private Status status;
    private EqualsDataService equalsDataService;
    private TransitRepo transitRepo;


    public VerificationDataTime(Status satus, EqualsDataService equalsDataService, TransitRepo transitRepo) {
        this.status = satus;
        this.equalsDataService = equalsDataService;
        this.transitRepo = transitRepo;
    }

    public void checkData() {
        Transit transitOne = null;
        Transit transitTwo = null;
        Transit transitThird = null;

        while (true) {
            try {

                Thread.sleep(1000);

                Transit first = transitRepo.findFirstByTurnstileAddrAndPassDirectionAndEventCodeOrderByIdOfEnterEventDesc("00250B00E0F9", "1", "17");
                Transit second = transitRepo.findFirstByTurnstileAddrAndPassDirectionAndEventCodeOrderByIdOfEnterEventDesc("00250B00E0F6", "1", "17");
                Transit third = transitRepo.findFirstByTurnstileAddrAndPassDirectionAndEventCodeOrderByIdOfEnterEventDesc("00250B00E0F7", "1", "17");
                status.setAlive(true);

                logger.error("Transit first " + first );
                logger.error("Transit second " + second );
                logger.error("Transit third " + third );

                transitOne = getTransit(transitOne, first);
                transitTwo = getTransit(transitTwo, second);
                transitThird = getTransit(transitThird, third);

            } catch (InterruptedException e) {
                status.setAlive(false);
                e.printStackTrace();
            }


        }
    }

    private Transit getTransit(Transit transitCash, Transit transitData) {
        if (transitCash != null) {
            if (!transitCash.equals(transitData)) {
                transitCash = transitData;
                equalsDataService.createData(transitData);
            }
        } else {
            transitCash = transitData;
            equalsDataService.createData(transitData);
        }
        return transitCash;
    }

}
