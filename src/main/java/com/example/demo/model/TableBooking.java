package com.example.demo.model;
import jakarta.persistence.*;
//import java.util.Optional;

@Entity
@Table(name="Tables_Available")
public class TableBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int table_no;

    @Column(name = "customer_id", nullable = true) // Allow null for unassigned customer
    private Integer customerId; 

    private int no_of_seats;

    public TableBooking() {
       this.customerId = 0;
    }

    public TableBooking(int no_of_seats) {
        this();
        this.no_of_seats = no_of_seats;
    }

    public int getTableNo()
    {
        return table_no;
    }

    public void setTableNo(int table_no)
    {
        this.table_no=table_no;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId=customerId;
    }

    public int getCustomerId()
    {
        return customerId;
    }

        public void setNoOfSeats(int no_of_seats)
        {
            this.no_of_seats=no_of_seats;
        }
        public int getNoOfSeats()
        {
            return no_of_seats;
        }



    }


