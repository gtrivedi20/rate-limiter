package com.mailchimp.service;

public interface RateLimitService {

    boolean isRateLimited(String uniqueToken);

}