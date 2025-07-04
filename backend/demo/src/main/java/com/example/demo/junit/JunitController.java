package com.example.demo.junit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JunitController {

	private static final Logger logger = LogManager.getLogger(JunitController.class);

	@GetMapping(value = "/test")
	public ResponseEntity<String> testJunit(@RequestParam String name) {
		logger.info("Test Junit method called in Junit controller");

		if (name != null && !name.isBlank()) {
			String result = JunitService.testJunitService(name);
			return ResponseEntity.ok(result);
		} else {
			logger.error("Test Junit method in Junit controller has error as name is required");
			return ResponseEntity.badRequest().body("Name is required");
		}
	}
}
