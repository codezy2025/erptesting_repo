package com.example.demo.stripe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.apiKey}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
