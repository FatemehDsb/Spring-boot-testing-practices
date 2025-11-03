package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public Greeting sayHello(){
        return service.greeting();
    }

    @GetMapping("/bye")
    public String sayGoodbye(){
        return "goddbye!";
    }

}
