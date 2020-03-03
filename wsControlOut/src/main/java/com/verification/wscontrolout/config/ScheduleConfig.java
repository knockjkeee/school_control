package com.verification.wscontrolout.config;

import com.verification.wscontrolout.domain.Result;
import com.verification.wscontrolout.service.GettingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class ScheduleConfig {

    @Autowired
    SimpMessagingTemplate template;

    private GettingData data;

    public ScheduleConfig(GettingData data) {
        this.data = data;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendAdhocMessages() {
        Result list = data.getData();
//        template.convertAndSend("/topic/user", new UserResponse("Fixed Delay Scheduler"));
//        template.convertAndSend("/topic/user", new UserResponse(list.toString()));
        template.convertAndSend("/topic/user",list);
    }

}
