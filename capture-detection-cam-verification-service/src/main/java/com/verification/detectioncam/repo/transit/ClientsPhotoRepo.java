package com.verification.detectioncam.repo.transit;

import com.verification.detectioncam.domain.transit.ClientsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsPhotoRepo extends JpaRepository<ClientsPhoto, Long> {

    ClientsPhoto findByIdOfClient(String IdOfClient);

}
