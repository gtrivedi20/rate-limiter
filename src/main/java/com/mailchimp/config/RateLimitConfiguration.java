package com.mailchimp.config;


public class RateLimitConfiguration {

    private int limit;

    private int seconds;

    public RateLimitConfiguration(int limit, int seconds) {
        this.limit = limit;
        this.seconds = seconds;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
