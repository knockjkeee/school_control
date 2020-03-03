package com.verification.match.controller;


import com.verification.match.domain.MyStompSessionHandlerMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class MatchResource {

    @Autowired
    MyStompSessionHandlerMatch handler;


//    @GetMapping("/rest/cam1")
    @GetMapping()
//    @RequestMapping("/rest/cam1")
    public String status() {
        boolean connect = handler.isStatusConnect();
        if (!connect) {
            return "Match service CAM-01 connected stopped";
        } else {
            return "Match service CAM-01 connected is alive";
        }
//        return "Match service CAM-01 stoped";
    }
//    @GetMapping("/info")
////    @RequestMapping("/info")
//    public String info() {
//        return "Match service CAM-01 worked";
//    }
}
