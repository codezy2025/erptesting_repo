package com.example.demo.junit;

import org.springframework.stereotype.Service;

@Service
public class JunitService {

	public static String testJunitService(String name) {
		return "Junit Test By " + name;
	}
	

}
