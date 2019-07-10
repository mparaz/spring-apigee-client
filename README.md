Spring Apigee Client
====================


To run:
```
./mvnw spring-boot:run -Dapigee.client_id=xxx -Dapigee.client_secret=xxx
```

The `client_id` and `client_secret` don't matter since the request couldn't be made, anyway.

Then:
`curl http://localhost:8080`

Produces the exception:
```
2019-07-10 23:43:23.068 ERROR 25513 --- [or-http-epoll-3] a.w.r.e.AbstractErrorWebExceptionHandler : [8f0c2fb4] 500 Server Error for HTTP GET "/hello"

java.lang.IllegalArgumentException: serverWebExchange must be null
	at org.springframework.util.Assert.isNull(Assert.java:159) ~[spring-core-5.1.8.RELEASE.jar:5.1.8.RELEASE]
	at org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository.loadAuthorizedClient(UnAuthenticatedServerOAuth2AuthorizedClientRepository.java:47) ~[spring-security-oauth2-client-5.1.5.RELEASE.jar:5.1.5.RELEASE]

```