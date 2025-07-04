package com.example.demo.junittest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.junit.JunitService;

@SpringBootTest
public class JunitServiceTest {

	@Test
	void testJunit() {
		String result = JunitService.testJunitService("user");
		assertEquals("Junit Test By user", result);
	}

}
