package com.example.springapigeeclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringApigeeClientApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("asyncSpringApigeeClient")
	private SpringApigeeClient springApigeeClient;

	public static void main(String[] args) {
		SpringApplication.run(SpringApigeeClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		springApigeeClient.authenticateAndGet();
	}

	// TODO Move this to a config class?
	@Bean
	WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations) {
		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth =
				new ServerOAuth2AuthorizedClientExchangeFilterFunction(
						clientRegistrations,
						new UnAuthenticatedServerOAuth2AuthorizedClientRepository());
		oauth.setDefaultClientRegistrationId("apigee");
		return WebClient.builder()
				.filter(oauth)
				.build();
	}
}
