package com.example.demo.twilio;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    private TwilioConfig config;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(config.getAccountSid(), config.getAuthToken());
        logger.info("Initialized Twilio with account SID: {}", config.getAccountSid());
    }

    public String sendSms(String to, String message) {
        try {
            Message sentMessage = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(config.getFromNumber()),
                    message
            ).create();

            logger.info("SMS sent successfully to {}. SID: {}", to, sentMessage.getSid());
            return sentMessage.getSid();
        } catch (ApiException e) {
            logger.error("Twilio API error while sending SMS to {}: {}", to, e.getMessage());
            return "Failed to send SMS: " + e.getMessage();
        } catch (Exception e) {
            logger.error("Unexpected error while sending SMS to {}: {}", to, e.getMessage(), e);
            return "Failed to send SMS due to unexpected error";
        }
    }
}
