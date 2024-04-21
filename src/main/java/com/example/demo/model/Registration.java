package com.example.demo.model;

import jakarta.persistence.*;
// import java.util.List;

@Entity
@Table(name="customer")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;
    private String username;
    private String password;

    public Registration() {
    }

    public Registration(String username,String password) {
            this.username = username;
            this.password=password;
        }
    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id= customer_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}