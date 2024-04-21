package com.example.demo.service;

import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;

import com.example.demo.repository.TableBookingRepository;
import com.example.demo.dto.TableBookingDto;
import com.example.demo.model.TableBooking;
//import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TableBookingService 
{
    private TableBookingRepository bookRepo;
    @Autowired
   public TableBookingService(TableBookingRepository bookRepo)
    {
        this.bookRepo=bookRepo;

    }

    
    /*public List<Map<String, Object>> getTableInfoForUnassignedCustomers() {
        // Fetch table IDs and number of seats for all table bookings where customer_id is 0
        List<Map<String, Object>> tableInfo = bookRepo.findTableInfoByCustomerId(0);
        return tableInfo;
      }*/

      public List<TableBookingDto> getTableInfoForUnassignedCustomers() {
        return bookRepo.findTableInfoByCustomerId(0);
    }

    public void updateCustomerIdForTable(int table_no, int customerId) {
        bookRepo.updateCustomerIdForTable(table_no, customerId);
    }
      
      


    


}
