package com.mailchimp.config;

import org.springframework.stereotype.Component;

@Component
public class GlobalConfiguration {

    private RateLimitConfiguration rateLimitConfiguration;

    public RateLimitConfiguration getRateLimitConfiguration() {
        return rateLimitConfiguration;
    }

    public void setRateLimitConfiguration(RateLimitConfiguration rateLimitConfiguration) {
        this.rateLimitConfiguration = rateLimitConfiguration;
    }

}
