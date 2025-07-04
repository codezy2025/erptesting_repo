package com.example.demo.retry;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class RetryService {

	private final RestTemplate restTemplate = new RestTemplate();
	private int attempt = 1;

	@Retry(name = "externalApiRetry", fallbackMethod = "fallbackMethod")
	public String callExternalApi() {
		System.out.println("Attempt #" + attempt + " at " + LocalDateTime.now());
		attempt++;

		// Replace this with your real API URL (simulate failure for demo)
		String url = "https://httpstat.us/500"; // Always returns 500 error

		// This will throw RestClientException
		return restTemplate.getForObject(url, String.class);
	}

	public String fallbackMethod(Exception ex) {
		return "External API call failed after retries. Fallback response: " + ex.getMessage();
	}
}