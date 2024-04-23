package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Payment;

@Service
public class PaymentService {
    // Service layer logic can be added if required

    public interface PaymentProcessor {
        void processPayment(Payment payment);
    }

    public class UpiPaymentProcessor implements PaymentProcessor {
        @Override
        public void processPayment(Payment payment) {
            // Implement UPI payment processing logic
        }
    }

    public class CashPaymentProcessor implements PaymentProcessor {
        @Override
        public void processPayment(Payment payment) {
            // Implement cash payment processing logic
        }
    }

    
}
