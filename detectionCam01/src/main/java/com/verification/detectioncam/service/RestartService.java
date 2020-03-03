package com.verification.detectioncam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Service;

@Service
public class RestartService {

    private RestartEndpoint restartEndpoint;

    @Autowired
    public RestartService(RestartEndpoint restartEndpoint) {
        this.restartEndpoint = restartEndpoint;
    }

    public void restart() {
        restartEndpoint.restart();
    }
}
