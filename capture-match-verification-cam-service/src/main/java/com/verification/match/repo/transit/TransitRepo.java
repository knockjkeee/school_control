package com.verification.match.repo.transit;

import com.verification.match.domain.transit.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransitRepo extends JpaRepository<Transit, Long> {

    Transit findFirstByTurnstileAddrAndPassDirectionOrderByIdOfEnterEventDesc
            (String turnstileAddr, String passDirection);

    Transit findFirstByTurnstileAddrAndPassDirectionAndEventCodeOrderByIdOfEnterEventDesc
            (String turnstileAddr, String passDirection, String eventCode);


    @Query(value = "SELECT * FROM enterevents where EvtDateTime = ?1 and TurnstileAddr = ?2", nativeQuery = true)
    Transit findTransit(String dataTime, String turnstileAddr);

    @Query(value = "SELECT * FROM enterevents where EvtDateTime > ?1 " +
            "and EvtDateTime <= ?2 " +
            "and TurnstileAddr = ?3 and PassDirection = ?4", nativeQuery = true)
    List<Transit> findCustomTransit(String dataTimeCustom, String dataTimeCurrent,
                                    String turnstileAddr, String passDirection);

}