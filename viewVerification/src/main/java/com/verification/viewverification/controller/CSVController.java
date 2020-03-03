package com.verification.viewverification.controller;

import com.verification.viewverification.domain.Result;
import com.verification.viewverification.util.CreateCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CSVController {

    private ApplicationContext context;

    @Autowired
    public CSVController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/download/data.csv")
    public void download(HttpServletResponse response) {
        MainController bean = context.getBean(MainController.class);
        Page<Result> result = bean.getResult();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=data.csv");
        response.setCharacterEncoding("windows-1251");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(CreateCSV.getDataCSV(result.getContent()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
