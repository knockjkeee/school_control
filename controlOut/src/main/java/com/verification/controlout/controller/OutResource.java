package com.verification.controlout.controller;


import com.verification.controlout.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class OutResource {

    @Autowired
    private Status status;

    @GetMapping()
    public String status() {
        if (!status.isAlive()) {
            return "OutMatch service connected stopped";
//            return "Match service CAM-01 connected is alive";
        } else {
            return "OutMatch service connected is alive";
        }
    }
}
