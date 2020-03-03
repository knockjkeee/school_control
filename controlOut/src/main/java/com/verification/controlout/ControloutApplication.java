package com.verification.controlout;

import com.verification.controlout.service.VerificationDataTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ControloutApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(ControloutApplication.class, args).close();
    }



    //TODO
    // change path file xml and change pw postgres and create db college --> name = schedule


    @Override
    public void run(String... args){
        VerificationDataTime bean = context.getBean(VerificationDataTime.class);
        try {
            bean.checkData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
