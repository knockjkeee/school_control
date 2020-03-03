package com.verification.viewcontrolout.controller;

import com.verification.viewcontrolout.domain.Result;
import com.verification.viewcontrolout.repo.ResultRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
@RequestMapping("/")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private Page<Result> result;


    public Page<Result> getResult() {
        return result;
    }

    private ResultRepo resultRepo;

    @Autowired
    public MainController(ResultRepo resultRepo) {
        this.resultRepo = resultRepo;
    }

    @GetMapping
    public String hello(
            Map<String, Object> model,
//                        @PageableDefault(sort = {"dataTransit"}, direction = Sort.Direction.DESC) Pageable pageable){
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 100) Pageable pageable) {
        Page<Result> page;


        page = resultRepo.findAll(pageable);
        result = resultRepo.findAll(pageable);
        model.put("page", page);
        model.put("url", "/");
        return "main";
    }

}
