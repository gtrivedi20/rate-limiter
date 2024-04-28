package com.mailchimp.controller;

import com.mailchimp.service.RateLimitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RateLimitController.class)
public class RateLimitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RateLimitService rateLimitService;

    @Test
    void rateLimitConfigureShouldReturnStatusOK() throws Exception {
        String requestBody = "{\"limit\": \"7\",\"interval\": \"3\"}";
        this.mockMvc.perform(post("/ratelimit/configure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void isRateLimitedShouldReturnStatusOK() throws Exception {
        when(rateLimitService.isRateLimited("user1")).thenReturn(true);
        this.mockMvc.perform(get("/ratelimit/isratelimited/user1"))
                .andExpect(status().isOk()).andExpect(content().string("Success for token user1"));
    }

    @Test
    void isRateLimitedShouldReturnTooManyRequest() throws Exception {
        when(rateLimitService.isRateLimited("user1")).thenReturn(false);
        this.mockMvc.perform(get("/ratelimit/isratelimited/user1"))
                .andExpect(status().isTooManyRequests()).andExpect(content().string("Rate limit exceeded for token user1"));
    }
}
