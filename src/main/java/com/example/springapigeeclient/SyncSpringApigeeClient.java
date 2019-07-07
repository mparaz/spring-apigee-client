package com.example.springapigeeclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SyncSpringApigeeClient implements SpringApigeeClient {
    private static final String clientUrl = "https://mparaz-eval-test.apigee.net/helloworld-client-credentials";

    private static final Logger logger = LoggerFactory.getLogger(SyncSpringApigeeClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void authenticateAndGet() {
        final HelloWorld helloWorld = restTemplate.getForObject(clientUrl, HelloWorld.class);
        logger.info("Retrieved using Client Credentials Grant Type: : {}", helloWorld);
    }
}
