package com.verification.match.service;

import com.verification.match.domain.transit.Transit;
import com.verification.match.repo.transit.TransitRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = "prototype")
public class CollegeLookupService {

    private static final Logger logger = LoggerFactory.getLogger(CollegeLookupService.class);

    @Autowired
    private TransitRepo transitRepo;

    public CollegeLookupService(TransitRepo transitRepo) {
        this.transitRepo = transitRepo;
    }

    public Transit findTransit(String turnstileAddr) {
        return transitRepo.findFirstByTurnstileAddrAndPassDirectionOrderByIdOfEnterEventDesc(turnstileAddr, "0");
    }

    public Transit findTransitWhereImageNull() {
        return transitRepo.findFirstByTurnstileAddrAndPassDirectionAndEventCodeOrderByIdOfEnterEventDesc("00250B00E0F9",
                "0", "17");
    }

    public List<Transit> findCustomTransit(String dataTimeCustom, String dataTimeCurrent, String turnstileAddr) {
        return transitRepo.findCustomTransit(dataTimeCustom, dataTimeCurrent,
                turnstileAddr, "0");
    }

}
