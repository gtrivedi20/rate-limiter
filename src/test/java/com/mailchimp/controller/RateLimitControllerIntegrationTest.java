package com.mailchimp.controller;


import com.mailchimp.config.GlobalConfiguration;
import com.mailchimp.config.RateLimitConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateLimitControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private GlobalConfiguration globalConfiguration;

    @LocalServerPort
    private int port;


    @Test
    public void rateLimitConfigureShouldReturnStatusOK() throws Exception {
        final String uri = "http://localhost:" + port + "/ratelimit/configure";
        HttpHeaders headers = new HttpHeaders();
        RateLimitConfiguration rateLimitConfiguration = new RateLimitConfiguration(7, 2);
        HttpEntity<RateLimitConfiguration> request = new HttpEntity<>(rateLimitConfiguration, headers);
        this.testRestTemplate.postForEntity(uri, request, HttpStatus.class);
    }

    @Test
    public void isRateLimitedShouldReturnStatusOK() throws Exception {
        ResponseEntity<String> responseEntity = null;
        final String uri = "http://localhost:" + port + "/ratelimit/isratelimited/123";
        HttpHeaders headers = new HttpHeaders();
        RateLimitConfiguration rateLimitConfiguration = new RateLimitConfiguration(7, 2);
        globalConfiguration.setRateLimitConfiguration(rateLimitConfiguration);

        for (int i = 0; i < rateLimitConfiguration.getLimit(); i++) {
            HttpEntity<RateLimitConfiguration> request = new HttpEntity<>(rateLimitConfiguration, headers);
            responseEntity = this.testRestTemplate.getForEntity(uri, String.class);
        }
        assertFalse(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void isRateLimitedShouldReturnTooManyRequest() throws Exception {
        ResponseEntity<String> responseEntity = null;
        final String uri = "http://localhost:" + port + "/ratelimit/isratelimited/123";
        HttpHeaders headers = new HttpHeaders();
        RateLimitConfiguration rateLimitConfiguration = new RateLimitConfiguration(7, 2);
        globalConfiguration.setRateLimitConfiguration(rateLimitConfiguration);

        for (int i = 0; i <= rateLimitConfiguration.getLimit(); i++) {
            HttpEntity<RateLimitConfiguration> request = new HttpEntity<>(rateLimitConfiguration, headers);
            responseEntity = this.testRestTemplate.getForEntity(uri, String.class);
        }
        assertTrue(responseEntity.getStatusCode().is4xxClientError());
    }

}
