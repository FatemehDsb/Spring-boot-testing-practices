package com.example.demo;


@org.springframework.stereotype.Service
public class Service {


    public Greeting greeting(){
        return new Greeting();
    }
}


// controller <-> service <-> repository <-> database

//configuration, entities, advice