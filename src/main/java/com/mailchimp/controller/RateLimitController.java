package com.mailchimp.controller;

import com.mailchimp.config.GlobalConfiguration;
import com.mailchimp.config.RateLimitConfiguration;
import com.mailchimp.service.RateLimitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratelimit")
public class RateLimitController {

    private final GlobalConfiguration globalConfiguration;

    private final RateLimitService rateLimitService;

    public RateLimitController(GlobalConfiguration globalConfiguration, RateLimitService rateLimitService) {
        this.globalConfiguration = globalConfiguration;
        this.rateLimitService = rateLimitService;
    }

    @PostMapping("/configure")
    public ResponseEntity<HttpStatus> configure(@RequestBody RateLimitConfiguration rateLimitConfiguration) {
        globalConfiguration.setRateLimitConfiguration(rateLimitConfiguration);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/isratelimited/{uniqueToken}")
    public ResponseEntity<String> isRateLimited(@PathVariable String uniqueToken) {
        if (!rateLimitService.isRateLimited(uniqueToken)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rate limit exceeded for token " + uniqueToken);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Success for token " + uniqueToken);
        }
    }
}
