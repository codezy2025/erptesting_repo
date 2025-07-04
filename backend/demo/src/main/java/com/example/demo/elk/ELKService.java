package com.example.demo.elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ELKService {
	private static final Logger logger = LoggerFactory.getLogger(ELKService.class);

    public void doSomething() {
        logger.info("INFO: Doing something important...");
        logger.warn("WARN: Something might be wrong...");
        logger.error("ERROR: Something went wrong!");
    }
}
