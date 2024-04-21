package com.example.demo.controller;

import com.example.demo.model.Registration;
import com.example.demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;


@Controller
public class RegistrationController {
    private RegistrationService regService;

    @Autowired
    public RegistrationController(RegistrationService regService)
    {
        this.regService = regService;
    }

    @GetMapping("/home")
    public String displayHome ()
    {
        return "home";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registration", new Registration());
        return "reg-form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registration") Registration registration, RedirectAttributes redirectAttributes) {
        regService.registerCustomer(registration.getUsername(), registration.getPassword());
        redirectAttributes.addFlashAttribute("successMessage", true);
        return "redirect:/registration";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model)
    {
        model.addAttribute("registration", new Registration());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redirectAttributes,HttpSession session) {
   
    int customerId = regService.findCustomerIdByUsernameAndPassword(username, password);
    
    //System.out.println("customer id is "+customerId);
    if (customerId!=0) 
    {
        session.setAttribute("customerId", customerId);
        session.setAttribute("username", username);
        redirectAttributes.addFlashAttribute("successMessage", true);
        return "redirect:/booktable";
    } else {
        
        redirectAttributes.addFlashAttribute("errorMessage", true);
        return "redirect:/login";
    }
}




}
