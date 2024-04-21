package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.repository.RegistrationRepository;
import com.example.demo.dto.RegistrationDto;
import com.example.demo.model.Registration;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RegistrationService 
{
    
    private RegistrationRepository registrationRepository;
    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository)
    {
        this.registrationRepository=registrationRepository;
    }

    public List<RegistrationDto> findAllCustomers()
    {
        List<Registration> customers = registrationRepository.findAll();
        return customers.stream().map((customer) -> mapToRegistrationDto(customer)).collect(Collectors.toList());
    }

    public void registerCustomer(String username, String password) {
        Registration registration = new Registration();
        registration.setUsername(username);
        registration.setPassword(password);
        registrationRepository.save(registration);
    }
    

    private RegistrationDto mapToRegistrationDto(Registration reg) {
        RegistrationDto regDto = new RegistrationDto();
        regDto.setCustomerId(reg.getCustomerId());
        regDto.setUsername(reg.getUsername());
        return regDto;
    }

    public int findCustomerIdByUsernameAndPassword(String username, String password) {
        Optional<Registration> userOptional = registrationRepository.findByUsernameAndPassword(username, password);
        return userOptional.map(Registration::getCustomerId).orElse(0);
    }

    /*public int getCustomerIdByUsername(String username) {
        Registration registration = registrationRepository.findByUsername(username);
        if (registration != null) {
            return registration.getCustomerId();
        } else {
            // Handle the case where the username is not found
           System.out.println("Username not found: " + username);
           return -1;
        }
    }*/

    
}
