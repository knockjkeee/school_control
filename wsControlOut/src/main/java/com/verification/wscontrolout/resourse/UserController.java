package com.verification.wscontrolout.resourse;

import com.verification.wscontrolout.domain.User;
import com.verification.wscontrolout.domain.UserResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


    @MessageMapping("/user")
    @SendTo("/topic/user")
    public UserResponse getUser(User user) {

        return new UserResponse(user.getName());
    }
}
