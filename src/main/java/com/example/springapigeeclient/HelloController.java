package com.example.springapigeeclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hey yo";
    }

    private static final String clientUrl = "https://mparaz-eval-test.apigee.net/helloworld-client-credentials";

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private WebClient webClient;


    private void authenticateAndGet() {

        webClient.get()
                .uri(clientUrl)
                .retrieve()
                .bodyToMono(HelloWorld.class)
                .map(helloWorld
                        -> "Retrieved using Client Credentials Grant Type: " + helloWorld)
                .subscribe(logger::info);
    }

}
