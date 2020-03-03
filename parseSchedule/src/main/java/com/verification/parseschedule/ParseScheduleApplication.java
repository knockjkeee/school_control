package com.verification.parseschedule;

import com.verification.parseschedule.service.ParseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;


@SpringBootApplication
public class ParseScheduleApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(ParseScheduleApplication.class, args).close();

    }

    //TODO
    // change path file xml and change pw postgres and create db college --> name = schedule

    @Override
    public void run(String... args) throws Exception {
        ParseServices bean = context.getBean(ParseServices.class);
        try {
            bean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
