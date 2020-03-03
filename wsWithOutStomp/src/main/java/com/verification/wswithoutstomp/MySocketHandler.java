package com.verification.wswithoutstomp;

import com.google.gson.Gson;
import com.verification.wswithoutstomp.domain.Result;
import com.verification.wswithoutstomp.service.GettingData;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
//@EnableScheduling
public class MySocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private GettingData data;

    public MySocketHandler(GettingData data) {
        this.data = data;
    }


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
//        Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);
//		/*for(WebSocketSession webSocketSession : sessions) {
//			webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
//		}*/
//        if (data == null) {
//            System.out.println("data nuullll");
//            System.out.println(data);
//        } else {
//            System.out.println(data);
//        }
//        System.out.println(data.getData());
//        System.out.println(sessions);
//        String s = new Gson().toJson(data.getData());
////        session.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
        session.sendMessage(message);

//        for (WebSocketSession webSocketSession : sessions) {
//            webSocketSession.sendMessage(message);
//        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        Result tempResult = null;
        int count = 0;
        while (true) {
            Result result = this.data.getData();
            Thread.sleep(5000);
//            TextMessage message = new TextMessage(count + new Gson().toJson(data.getData()));
            if (tempResult == null) {
                System.out.println("tempResult == null");
                tempResult = result;
                TextMessage message = new TextMessage(new Gson().toJson(tempResult));
                handleTextMessage(session, message);
            } else {
                if (tempResult.getUsernameTransit().equals(result.getUsernameTransit()) ) {
                    System.out.println("tempResult != result");
                    TextMessage message = new TextMessage(new Gson().toJson(result));
                    handleTextMessage(session, message);
                    tempResult = result;
                } else {
//                    System.out.println("asdsadsadsadadassadasdsadsadas");
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
//        super.afterConnectionClosed(session, status);
    }
}
