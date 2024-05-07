package com.mailchimp.service;


import com.mailchimp.config.GlobalConfiguration;
import com.mailchimp.config.RateLimitConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RateLimitServiceTest {

    @Mock
    private GlobalConfiguration globalConfiguration;

    @InjectMocks
    private RateLimitServiceImpl rateLimitService;

    @Test
    public void rateLimitReturnFalse() {
        boolean returnResult = true;
        globalConfiguration.setRateLimitConfiguration(new RateLimitConfiguration(7, 2));
        Mockito.when(globalConfiguration.getRateLimitConfiguration()).thenReturn(new RateLimitConfiguration(7, 2));
        for (int i = 0; i <= 8; i++) {
            returnResult = rateLimitService.isRateLimited("user1");
        }
        Assertions.assertFalse(returnResult);
    }

    @Test
    public void rateLimitReturnTrue() {
        boolean returnResult = false;
        globalConfiguration.setRateLimitConfiguration(new RateLimitConfiguration(7, 2));
        Mockito.when(globalConfiguration.getRateLimitConfiguration()).thenReturn(new RateLimitConfiguration(7, 2));
        for (int i = 0; i < 7; i++) {
            returnResult = rateLimitService.isRateLimited("user1");
        }
        Assertions.assertTrue(returnResult);
    }
}
