package com.example.springapigeeclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

// Reference: https://www.baeldung.com/spring-webclient-oauth2

@Component
public class AsyncSpringApigeeClient implements SpringApigeeClient {
    private static final String clientUrl = "https://mparaz-eval-test.apigee.net/helloworld-client-credentials";

    private static final Logger logger = LoggerFactory.getLogger(AsyncSpringApigeeClient.class);

    @Autowired
    private WebClient webClient;

    @Override
    public void authenticateAndGet() {

        webClient.get()
                .uri(clientUrl)
                .retrieve()
                .bodyToMono(HelloWorld.class)
                .map(helloWorld
                        -> "Retrieved using Client Credentials Grant Type: " + helloWorld)
                .subscribe(logger::info);
    }
}
