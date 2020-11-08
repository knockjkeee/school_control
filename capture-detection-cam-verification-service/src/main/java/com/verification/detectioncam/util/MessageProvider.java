package com.verification.detectioncam.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verification.detectioncam.domain.Message;
import com.verification.detectioncam.domain.iss.Subscribe;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class MessageProvider {

    public static Subscribe subscribe(WebSocketSession session, TextMessage message) throws IOException {
        Message msg = new Message();
        msg.setText(message.getPayload());
        msg.setFrom(String.valueOf(session.getLocalAddress()));
        Subscribe subscribe = new ObjectMapper().readValue(msg.getText(), Subscribe.class);
        return subscribe;
    }


}
