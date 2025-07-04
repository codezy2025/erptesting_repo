package com.example.demo.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

	private static final Logger logger = LogManager.getLogger(Scheduler.class);

	@Scheduled(cron = "${scheduler.cron.job}")
	public void runScheduler() {
		logger.info("Scheduler called");
		// Enter your scheduler logic here
	}
}
