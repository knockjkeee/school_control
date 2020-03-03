package com.verification.match.repo.result;

import com.verification.match.domain.result.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<Result, Long> {

    Result findFirstByOrderByIdDesc();

    Result findFirstByUsernameTransitAndDataTransitOrderById(String name, String date);

//    Transit findFirstByTurnstileAddrAndPassDirectionOrderByIdOfEnterEventDesc
//            (String turnstileAddr, String passDirection);


}
