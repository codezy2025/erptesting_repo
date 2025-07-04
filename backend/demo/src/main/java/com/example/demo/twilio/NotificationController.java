package com.example.demo.twilio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private SmsService smsService;

    @PostMapping("/sms")
    public String sendSms(@RequestParam String to, @RequestParam String message) {
        logger.info("Received request to send SMS to {}", to);
        String result = smsService.sendSms(to, message);

        if (result.startsWith("Failed")) {
            logger.warn("SMS sending failed for {}: {}", to, result);
        } else {
            logger.info("SMS sending succeeded for {}. Message SID: {}", to, result);
        }

        return result;
    }
}
