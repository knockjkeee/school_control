package com.verification.detectioncam.service;

import com.verification.detectioncam.domain.MyStompSessionHandlerDetection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.util.concurrent.ExecutionException;


@Service
public class SpringWebsocketWithoutStompClientServiceDetection {
    private static final Logger logger = LoggerFactory.getLogger(SpringWebsocketWithoutStompClientServiceDetection.class);

    @Value("${iss.ws.request}")
    private String link;

    private MyStompSessionHandlerDetection handler;

    @Autowired
    public SpringWebsocketWithoutStompClientServiceDetection(MyStompSessionHandlerDetection handler) {
        this.handler = handler;
    }

    public void connect() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
        webSocketHttpHeaders.add("Content-Type", "application/json");
        webSocketHttpHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");

        try {
            WebSocketSession webSocketSession = webSocketClient.doHandshake(handler,
                    webSocketHttpHeaders, URI.create(link)).get();
        } catch (InterruptedException e) {
            logger.info(" SpringWebsocketWithoutStompClientServiceDetection connect() - ");
            e.printStackTrace();
        } catch (ExecutionException e) {
            logger.info(" SpringWebsocketWithoutStompClientServiceDetection connect() - ");
            e.printStackTrace();
        }
    }
}
