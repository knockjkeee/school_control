package com.verification.detectioncam.domain;


import com.verification.detectioncam.domain.iss.Subscribe;
import com.verification.detectioncam.service.ISSLookupService;
import com.verification.detectioncam.service.RestartService;
import com.verification.detectioncam.util.MessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class MyStompSessionHandlerDetection extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyStompSessionHandlerDetection.class);
    private boolean statusConnect = false;

    private RestartService restartService;
    private ISSLookupService issLookupService;
    private ApplicationContext context;

    public boolean isStatusConnect() {
        return statusConnect;
    }

    @Autowired
    public MyStompSessionHandlerDetection(RestartService restartService, ISSLookupService issLookupService, ApplicationContext context) {
        this.restartService = restartService;
        this.issLookupService = issLookupService;
        this.context = context;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        Subscribe subscribe = MessageProvider.subscribe(session, message);
        issLookupService.checkMainLinkDetection(subscribe);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        statusConnect = true;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("established connection detection ws - " + session);
        TextMessage messages = new TextMessage("{\"plane\":\"detections\",\"action\":\"subscribe\",\"feeds\":[\"1\", \"2\"]}");
        try {
            session.sendMessage(messages);
        } catch (IOException e) {
            logger.info("MyStompSessionHandlerDetection afterConnectionEstablished- ");
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        statusConnect = false;
        logger.info("MyStompSessionHandlerDetection connection close- " + session);
        TextMessage messages = new TextMessage("{\"plane\":\"detections\",\"action\":\"unsubscribe\",\"feeds\":[\"1\", \"2\"]}");

//        SpringWebsocketWithoutStompClientServiceDetection bean = context.getBean(SpringWebsocketWithoutStompClientServiceDetection.class);
//        bean.connect();


//        ThreadPoolTaskExecutor bean = context.getBean(ThreadPoolTaskExecutor.class);
//        bean.shutdown();

        @SuppressWarnings("unchecked")
        CompletableFuture<ThreadPoolTaskExecutor> executor = (CompletableFuture<ThreadPoolTaskExecutor>) context.getBean("executor");
        ThreadPoolTaskExecutor taskExecutor = executor.get();
        taskExecutor.shutdown();


        restartService.restart();


        try {
            session.sendMessage(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
