package com.mailchimp;

import com.mailchimp.controller.RateLimitController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class RateLimitApplicationTest {

    @Autowired
    private RateLimitController rateLimitController;

    @Test
    void contextLoads() throws Exception {
        assertThat(rateLimitController).isNotNull();
    }

}
