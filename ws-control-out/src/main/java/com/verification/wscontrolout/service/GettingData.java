package com.verification.wscontrolout.service;

import com.verification.wscontrolout.domain.Result;
import com.verification.wscontrolout.repo.ResultRepo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GettingData {

    private ResultRepo resultRepo;
    private Result tempResult;

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

    //    @PostConstruct
    public void addData() {
        Result result = new Result();

//        result.setIdTransit(1122334455L);
//        result.setUsernameTransit("Аарон Пол");
//        result.setClientsGroup("11-Г");
//        result.setTurnstileAddr("00xx00xx11");
//        result.setDataTransit("11.12.2019");
//        result.setTimeTransit("12-00");
//        result.setNameLessons("information is absent");
//        result.setPersonFaceImageUrl("https://img2.akspic.ru/fit/31769-aaron_pol-lico-brov-lob-dzhessi_pinkman-x750.jpg");
//        result.setResult("Ok");
//
//
//        resultRepo.save(result);

        Result result2 = new Result();

        result2.setIdTransit(6677889900L);
        result2.setUsernameTransit("Дичь Каприот");
        result2.setClientsGroup("9-А");
        result2.setTurnstileAddr("11xx11xx22");
        result2.setDataTransit("10.12.2019");
        result2.setTimeTransit("15-00");
        result2.setNameLessons("15-00 ОБЖ Steven Seagal");
        result2.setPersonFaceImageUrl("https://habrastorage.org/webt/59/e4/27/59e427431c624910906990.png");
        result2.setResult("Прогул");

        resultRepo.save(result2);


    }


}
