package com.verification.startview.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Controller
@RequestMapping("/")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "fallback", groupKey = "Main",
            commandKey = "main",
            threadPoolKey = "MainThread")
    @GetMapping
    public String main(Map<String, Object> model) {
        String url = "http://match-cam1/info";
        String object;
        try {
            object = restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            model.put("status", "false");
            return "main";
        }

        assert object != null;
        if (object.contains("alive")) {
            model.put("status", "true");
        } else {
            model.put("status", "false");
        }
        return "main";
    }

    @ExceptionHandler(ResourceAccessException.class)
    public String fallback(Throwable hystrixCommand, Map<String, Object> model) {
        model.put("status", "false");
        return "main";
    }

}
