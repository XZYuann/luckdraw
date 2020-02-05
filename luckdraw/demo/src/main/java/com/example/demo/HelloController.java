package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {
    @Autowired
    helloService helloservice;
    @Autowired
    private studentRepository repository;
    @GetMapping("/luckydog")
    public student findById()
    {
        return repository.findById(id);
    }

    }

