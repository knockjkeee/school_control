package com.verification.match.repo.transit;

import com.verification.match.domain.transit.ClientsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsPhotoRepo extends JpaRepository<ClientsPhoto, Long> {

    ClientsPhoto findByIdOfClient(String IdOfClient);

}
