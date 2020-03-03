package com.verification.match.domain;


import com.verification.match.domain.iss.Subscribe;
import com.verification.match.service.ISSLookupService;
import com.verification.match.service.RestartService;
import com.verification.match.util.MessageProvider;
import com.zaxxer.hikari.pool.HikariPool;
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
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Component
public class MyStompSessionHandlerMatch extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyStompSessionHandlerMatch.class);
    private boolean statusConnect = false;


    public boolean isStatusConnect() {
        return statusConnect;
    }

    private RestartService restartService;
    private ISSLookupService issLookupService;
    private ApplicationContext context;


    @Autowired
    public MyStompSessionHandlerMatch(RestartService restartService, ISSLookupService issLookupService, ApplicationContext context) {
        this.restartService = restartService;
        this.issLookupService = issLookupService;
        this.context = context;
    }


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        Subscribe subscribe = MessageProvider.subscribe(session, message);
        issLookupService.checkMainLinkMatch(subscribe);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        statusConnect = true;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("established connection match ws- " + session);
        TextMessage messages = new TextMessage("{\"plane\":\"matches\",\"action\":\"subscribe\",\"feeds\":[\"1\", \"2\"]}");
        try {
            session.sendMessage(messages);
        } catch (IOException e) {
            logger.info("MyStompSessionHandlerMatch afterConnectionEstablished- " );
            e.printStackTrace();
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        statusConnect = false;
        logger.info("MyStompSessionHandlerMatch connection close- " + session);
        TextMessage messages = new TextMessage("{\"plane\":\"matches\",\"action\":\"unsubscribe\",\"feeds\":[\"1\", \"2\"]}");
//        SpringWebsocketWithoutStompClientServiceMatch bean = context.getBean(SpringWebsocketWithoutStompClientServiceMatch.class);
//        bean.connect();

//        ThreadPoolTaskExecutor bean = context.getBean(ThreadPoolTaskExecutor.class);
//        bean.shutdown();

        @SuppressWarnings("unchecked")
        CompletableFuture<ThreadPoolTaskExecutor> executor = (CompletableFuture<ThreadPoolTaskExecutor>) context.getBean("executor");
        ThreadPoolTaskExecutor taskExecutor = executor.get();
        if (taskExecutor !=null) {
            taskExecutor.shutdown();
        }


        restartService.restart();
//        RestartService res = context.getBean(RestartService.class);
//        res.restart();


        try {
            session.sendMessage(messages);
        } catch (IOException e) {
            logger.info("MyStompSessionHandlerMatch afterConnectionClosed- " );
            e.printStackTrace();
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}
