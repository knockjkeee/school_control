package com.verification.match;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@EnableDiscoveryClient
@SpringBootApplication
@Async
public class MatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchApplication.class, args).close();
    }

    @Bean(name = "executor")
    public CompletableFuture<Executor> taskExecutorWS() { //taskExecutorWS
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("CompareDataTwoService-");
        executor.initialize();
        return CompletableFuture.completedFuture(executor);
    }

}
