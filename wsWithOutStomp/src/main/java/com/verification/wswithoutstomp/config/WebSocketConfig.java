package com.verification.wswithoutstomp.config;


import com.verification.wswithoutstomp.MySocketHandler;
import com.verification.wswithoutstomp.repo.ResultRepo;
import com.verification.wswithoutstomp.service.GettingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
//public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {


    @Autowired
    private GettingData data;

    @Autowired
    private ResultRepo resultRepo;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MySocketHandler(data), "/ws/mobile");
    }

    @Bean
    public GettingData data() {
        data = new GettingData(resultRepo);
        return data;
    }

}
