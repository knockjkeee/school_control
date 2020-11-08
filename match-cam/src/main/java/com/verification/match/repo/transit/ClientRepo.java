package com.verification.match.repo.transit;

import com.verification.match.domain.transit.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByIdOfClient(String id);
}
