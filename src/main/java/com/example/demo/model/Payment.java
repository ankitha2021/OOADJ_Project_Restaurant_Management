package com.example.demo.model;
import jakarta.persistence.*;
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private double amount;

    public Payment() {
    }

    public Payment(double amount) {
        this.amount = amount;
    }

    public int getOrderId()
    {
        return orderId;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount=amount;
    }


    
}
