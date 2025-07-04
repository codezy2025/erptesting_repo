package com.example.demo.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private StripePaymentService paymentService;

    @PostMapping("/create")
    public String createPayment(@RequestParam long amount, @RequestParam(defaultValue = "usd") String currency) {
        logger.info("Request to create payment intent for amount: {} {}", amount, currency);
        try {
            PaymentIntent paymentIntent = paymentService.createPaymentIntent(amount, currency);
            return paymentIntent.toJson();
        } catch (StripeException e) {
            logger.error("Stripe error: {}", e.getMessage());
            return "Error creating payment intent: " + e.getMessage();
        }
    }
}
