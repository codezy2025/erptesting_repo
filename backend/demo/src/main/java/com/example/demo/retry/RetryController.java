package com.example.demo.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RetryController {

	@Autowired
	private RetryService retryService;

	@GetMapping("/retry-test")
	public String testRetry() {
		return retryService.callExternalApi();
	}
}
