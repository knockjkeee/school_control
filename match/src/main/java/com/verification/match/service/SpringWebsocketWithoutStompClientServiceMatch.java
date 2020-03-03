package com.verification.match.service;

import com.verification.match.domain.MyStompSessionHandlerMatch;
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

//import org.springframework.web.socket.WebapSocketHttpHeaders;

@Service
public class SpringWebsocketWithoutStompClientServiceMatch {
    private static final Logger logger = LoggerFactory.getLogger(SpringWebsocketWithoutStompClientServiceMatch.class);

    @Value("${iss.ws.request}")
    private String link;

    private MyStompSessionHandlerMatch handler;

    @Autowired
    public SpringWebsocketWithoutStompClientServiceMatch(MyStompSessionHandlerMatch handler) {
        this.handler = handler;
    }

    public void connect() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
        webSocketHttpHeaders.add("Content-Type", "application/json");
        webSocketHttpHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");

        try {
            WebSocketSession webSocketSession = webSocketClient.doHandshake(handler,
                    webSocketHttpHeaders, URI.create(link)).get();
        } catch (InterruptedException e) {
            logger.info(" SpringWebsocketWithoutStompClientServiceMatch connect() - ");
            e.printStackTrace();
        } catch (ExecutionException e) {
            logger.info(" SpringWebsocketWithoutStompClientServiceMatch connect() - ");
            e.printStackTrace();
        }
    }
}
