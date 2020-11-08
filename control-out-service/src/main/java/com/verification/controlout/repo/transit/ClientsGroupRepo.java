package com.verification.controlout.repo.transit;

import com.verification.controlout.domain.transit.ClientsGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsGroupRepo extends JpaRepository<ClientsGroup, Long> {

    ClientsGroup findByIdOfClientsGroup(String IdOfClientsGroup);

}
