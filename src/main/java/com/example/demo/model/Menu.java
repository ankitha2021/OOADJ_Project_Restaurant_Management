package com.example.demo.model;
import jakarta.persistence.*;
//import java.util.Optional;

@Entity
@Table(name="Menu_items")

public class Menu 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;
    private String item_name;
    private int price;

    public Menu()
    {

    }

    public Menu(String item_name,int price)
    {
        this.item_name=item_name;
        this.price=price;
    }

    public int getItemId()
    {
        return item_id;
    }
    public void setItemId(int item_id)
    {
        this.item_id=item_id;
    }
    public String getItemName()
    {
        return item_name;
    }
    public void setItemName(String item_name)
    {
        this.item_name=item_name;
    }
    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price=price;
    }
    
}
