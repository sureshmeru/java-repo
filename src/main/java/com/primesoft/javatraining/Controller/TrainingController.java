package com.primesoft.javatraining.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/training")
public class TrainingController {

    @GetMapping("getHello")
    public String getTraining(){
        return "Hello World Suresh";
    }
}
