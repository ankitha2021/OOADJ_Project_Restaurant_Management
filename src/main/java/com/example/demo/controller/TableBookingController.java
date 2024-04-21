package com.example.demo.controller;
import com.example.demo.model.TableBooking;
import com.example.demo.dto.TableBookingDto;
import com.example.demo.service.TableBookingService;

import java.util.List;
//import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;

@Controller
public class TableBookingController 
{
    private TableBookingService bookservice;

    @Autowired
    public TableBookingController(TableBookingService bookService)
    {
        this.bookservice=bookService;
    }

    @GetMapping("/booktable")
    public String displayTables(Model model) 
    {
      //Integer customerId = (Integer) session.getAttribute("customerId");
    
    List<TableBookingDto> tableInfo = bookservice.getTableInfoForUnassignedCustomers();
    //System.out.println("customer id "+customerId);
    model.addAttribute("tableInfo", tableInfo);

    /*for (TableBookingDto dto : tableInfo) {
      System.out.println("Table No: " + dto.getTableNo() + ", Number of Seats: " + dto.getNoOfSeats());
    }*/
    return "book_table";
    }


 /*@PostMapping("/booktable")
public String processTableSelection(@RequestParam(value = "tableChoices", required = false) List<String> tableChoices,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) 
{
    if (tableChoices != null && !tableChoices.isEmpty()) 
    {
      int customerId = (Integer) session.getAttribute("customerId");
      for (String tableChoice : tableChoices) 
      {
        int table_no = Integer.parseInt(tableChoice);
        bookservice.updateCustomerIdForTable(table_no, customerId);
      }
        redirectAttributes.addFlashAttribute("successBooked", true);
        return "redirect:/booktable?successBooked=true"; 
    } 
    else 
    {
        return "redirect:/booktable"; 
    }
}*/

@PostMapping("/booktable")
public String processTableSelection(@RequestParam(value = "tableChoices", required = false) List<String> tableChoices,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
    if (tableChoices != null && !tableChoices.isEmpty()) {
        int customerId = (Integer) session.getAttribute("customerId");
        StringBuilder bookedTables = new StringBuilder();
        for (String tableChoice : tableChoices) 
        {
            int tableNo = Integer.parseInt(tableChoice);
            bookservice.updateCustomerIdForTable(tableNo, customerId);
            if (bookedTables.length() > 0) {
                bookedTables.append(", ");
            }
            bookedTables.append(tableNo);
        }
        redirectAttributes.addFlashAttribute("successBooked", true);
        redirectAttributes.addFlashAttribute("bookedTables", bookedTables.toString());
        session.setAttribute("bookedTables", bookedTables.toString());
        return "redirect:/booktable?successBooked=true";
    }
    return "redirect:/booktable";
}


}
