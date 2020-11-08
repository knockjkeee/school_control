package com.verification.detectioncam.service;

import com.verification.detectioncam.domain.result.Result;
import com.verification.detectioncam.repo.result.ResultRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class SaveCompareTwoDataService {
    private static final Logger logger = LoggerFactory.getLogger(SaveCompareTwoDataService.class);

    @Autowired
    private ResultRepo repo;

    public SaveCompareTwoDataService(ResultRepo repo) {
        this.repo = repo;
    }

    public void saveData(Result temp) {
        repo.save(temp);
    }
}
