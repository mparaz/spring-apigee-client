package com.example.springapigeeclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${api.url}")
    private String clientUrl;

    @Autowired
    private WebClient webClient;

    @GetMapping("/hello")
    public Mono<HelloWorld> hello() {
        // Just background...
        return webClient.get()
                .uri(clientUrl)
                .retrieve()
                .bodyToMono(HelloWorld.class);

    }

}
