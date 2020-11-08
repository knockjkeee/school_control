package com.verification.detectioncam.controller;


import com.verification.detectioncam.domain.MyStompSessionHandlerDetection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class DetectionResource {

    @Autowired
    MyStompSessionHandlerDetection handler;

//    @GetMapping("/rest/cam1")
    @GetMapping()
//    @RequestMapping("/rest/cam1")
    public String status() {
        boolean connect = handler.isStatusConnect();
        if (!connect) {
            return "Detection service CAM-01 connected stopped";
        } else {
            return "Detection service CAM-01 connected is alive";
        }
    }

//
//    @GetMapping("/info")
////    @RequestMapping("/info")
//    public String info() {
//        return "Detection service CAM-01 worked";
//    }
}
