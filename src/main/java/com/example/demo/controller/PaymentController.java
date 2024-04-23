package com.example.demo.controller;

import com.example.demo.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import com.example.demo.service.PaymentService;
import com.example.demo.service.PaymentService.PaymentProcessor;
import com.example.demo.repository.PaymentRepositoryFactory;


@Controller
public class PaymentController {
    private PaymentService paymentService;
  

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping("/payment")
    public String showPaymentOptions(Model model) {
        return "payment_options";
    }

    @PostMapping("/processPayment")
    public String processPayment(@RequestParam("paymentMethod") String paymentMethod,
                                 HttpSession session,Model model) {
       
        double totalPrice = (double) session.getAttribute("totalPrice");
        model.addAttribute("totalPrice", totalPrice);       
       
        PaymentProcessor paymentProcessor = PaymentRepositoryFactory.createPaymentProcessor(paymentMethod);

        if ("upi".equals(paymentMethod)) {
            return "upi_payment";
        } else if ("cash".equals(paymentMethod)) {
            return "cash_payment";
        } 
            return "bill";
    }
}
