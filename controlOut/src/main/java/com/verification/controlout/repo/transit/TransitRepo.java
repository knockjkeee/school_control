package com.verification.controlout.repo.transit;


import com.verification.controlout.domain.transit.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitRepo extends JpaRepository<Transit, Long> {

    Transit findFirstByTurnstileAddrAndPassDirectionOrderByIdOfEnterEventDesc
            (String turnstileAddr, String passDirection);

    Transit findFirstByTurnstileAddrAndPassDirectionAndEventCodeOrderByIdOfEnterEventDesc
            (String turnstileAddr, String passDirection, String eventCode);

}
