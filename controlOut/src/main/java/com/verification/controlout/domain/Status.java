package com.verification.controlout.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Status {
    private boolean isAlive = false;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    //    public boolean isAlive() {
//        return isAlive;
//    }
//
//    public void setAlive(boolean alive) {
//        isAlive = alive;
//    }
}
