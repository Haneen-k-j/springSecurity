package com.SpringJWT.SpringJWT.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/demo-controller")
public class DemoController {

    @RequestMapping
    public ResponseEntity<String> sayHello(){
     return ResponseEntity.ok("hello from secured");
    }
}
