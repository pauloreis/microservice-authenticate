package br.com.microservices.authenticate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/1")
    public ResponseEntity<?> teste1(){
        return ResponseEntity.ok("TEste1");
    }

    @GetMapping("/2")
    public ResponseEntity<?> teste2(){
        return ResponseEntity.ok("TEste2");
    }
}
