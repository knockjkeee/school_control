package com.verification.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class RestartService {

    private RestartEndpoint restartEndpoint;
    private ApplicationContext context;

    @Autowired
    public RestartService(RestartEndpoint restartEndpoint, ApplicationContext context) {
        this.restartEndpoint = restartEndpoint;
        this.context = context;
    }

    @ExceptionHandler(SQLException.class)
    public void restart(){
        @SuppressWarnings("unchecked")
        CompletableFuture<ThreadPoolTaskExecutor> executor = (CompletableFuture<ThreadPoolTaskExecutor>) context.getBean("executor");
        ThreadPoolTaskExecutor taskExecutor = null;
        try {
            taskExecutor = executor.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (taskExecutor != null) {
            taskExecutor.shutdown();
        }
        restartEndpoint.restart();
    }
}
