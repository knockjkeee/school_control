package com.verification.match.service;

import com.verification.match.domain.transit.Client;
import com.verification.match.domain.transit.ClientsPhoto;
import com.verification.match.repo.transit.ClientRepo;
import com.verification.match.repo.transit.ClientsPhotoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class CollegeLookupServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(CollegeLookupServiceClient.class);

    private ClientRepo clientRepo;
    private ClientsPhotoRepo clientsPhotoRepo;

    @Autowired
    public CollegeLookupServiceClient(ClientRepo clientRepo, ClientsPhotoRepo clientsPhotoRepo) {
        this.clientRepo = clientRepo;
        this.clientsPhotoRepo = clientsPhotoRepo;
    }

    public Client findTransitPersonalClient(String id) {
        return clientRepo.findByIdOfClient(id);

    }

    public ClientsPhoto findTransitPersonalClientPhoto(String IdOfClient) {
        return clientsPhotoRepo.findByIdOfClient(IdOfClient);
    }


}
