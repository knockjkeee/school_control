package com.verification.wswithoutstomp.service;

import com.verification.wswithoutstomp.MySocketHandler;
import com.verification.wswithoutstomp.domain.Result;
import com.verification.wswithoutstomp.repo.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Service
public class GettingData {

    private ResultRepo resultRepo;
    private Result tempResult;

    @Autowired
    private ApplicationContext context;

    public GettingData(ResultRepo resultRepo) {
        this.resultRepo = resultRepo;
    }


    public Result getData() {
        Result firstFindResult = resultRepo.findFirstByResultOrderByIdDesc("Прогул");

        if (tempResult == null) {
            tempResult = firstFindResult;
        } else {
            if (tempResult != firstFindResult) {
                tempResult = firstFindResult;
                return tempResult;
            }
        }
        return firstFindResult;
    }

}




