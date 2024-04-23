package com.example.demo.repository;
import com.example.demo.service.PaymentService;
import com.example.demo.service.PaymentService.PaymentProcessor;
import com.example.demo.model.Payment;


public class PaymentRepositoryFactory {
    public static PaymentProcessor createPaymentProcessor(String paymentMethod) {
        switch (paymentMethod) {
            case "upi":
                return new PaymentService().new UpiPaymentProcessor();
            case "cash":
                return new PaymentService().new CashPaymentProcessor();
            default:
                throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
    }
}
