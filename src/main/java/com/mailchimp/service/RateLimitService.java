package com.mailchimp.service;

import com.mailchimp.config.GlobalConfiguration;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {

    private final GlobalConfiguration globalConfiguration;

    private final ConcurrentHashMap<String, TokenRequestCounter> tokenRequestCounters;

    /**
     * @param globalConfiguration
     */
    public RateLimitService(GlobalConfiguration globalConfiguration) {
        this.tokenRequestCounters = new ConcurrentHashMap<>();
        this.globalConfiguration = globalConfiguration;
    }


    /**
     * @param uniqueToken
     * @return
     */
    public boolean isRateLimited(String uniqueToken) {
        long currentTime = System.currentTimeMillis() / 1000;
        TokenRequestCounter counter = tokenRequestCounters.getOrDefault(uniqueToken, new TokenRequestCounter());

        // Need to Clear expired entries
        if (currentTime - counter.getStartTime() > globalConfiguration.getRateLimitConfiguration().getSeconds()) {
            counter.reset(currentTime);
        }

        // if the token has exceeded the limit
        if (counter.getRequestCount() < globalConfiguration.getRateLimitConfiguration().getLimit()) {
            counter.incrementCount();
            tokenRequestCounters.put(uniqueToken, counter);
            return true;
        } else {
            return false;
        }
    }


    private static class TokenRequestCounter {
        private int requestCount;
        private long startTime;

        public TokenRequestCounter() {
            this.requestCount = 0;
            this.startTime = System.currentTimeMillis() / 1000;
        }

        public void reset(long currentTime) {
            this.requestCount = 0;
            this.startTime = currentTime;
        }

        public void incrementCount() {
            this.requestCount++;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public long getStartTime() {
            return startTime;
        }
    }

}
