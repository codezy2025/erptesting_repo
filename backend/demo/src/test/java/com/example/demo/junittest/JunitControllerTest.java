package com.example.demo.junittest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JunitControllerTest {

	private static final Logger logger = LogManager.getLogger(JunitControllerTest.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testGreetingEndpoint() throws Exception {
		logger.info("Junit test case controller called");
		String response = restTemplate.getForObject("/test?name=user", String.class);
		assertEquals("Junit Test By user", response);
	}

}
