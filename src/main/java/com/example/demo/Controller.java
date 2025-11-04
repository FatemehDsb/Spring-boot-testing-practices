package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

@Value("${app.welcome-message}")
    String message;
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
        return message;
    }

}
