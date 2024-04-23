package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.http.HttpSession;
import com.example.demo.service.TableBookingService;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private TableBookingService bookservice;
    public MenuController(TableBookingService tableBookingService) 
    {
        this.bookservice = bookservice;
    }

    private Map<Menu, Integer> orderItems = new HashMap<>();
    private List<Menu> orderedItems;
    private List<Integer> orderQuantities;
    private List<Double> itemTotalPrices;
    private double totalPrice;

    @GetMapping("/menu")
    public String showMenu(Model model) {
        model.addAttribute("menuItems", menuService.getAllMenuItems());
        return "menu";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam(value = "itemId", required = false) List<Integer> itemIds,
                             @RequestParam(value = "quantity", required = false) List<Integer> quantities) {
        if (itemIds != null && quantities != null) {
            for (int i = 0; i < itemIds.size(); i++) {
                int itemId = itemIds.get(i);
                Integer quantity = quantities.get(i);
                if (quantity > 0) {
                    Menu menuItem = menuService.getMenuItemById(itemId);
                    if (menuItem != null) {
                        orderItems.put(menuItem, quantity);
                    }
                }
            }
        }
        return "redirect:/orderSummary";
    }

    private void calculateOrderSummary() {
        totalPrice = 0;
        orderedItems = new ArrayList<>(orderItems.keySet());
        orderQuantities = new ArrayList<>(orderItems.values());
        itemTotalPrices = new ArrayList<>();

        for (int i = 0; i < orderedItems.size(); i++) {
            Menu item = orderedItems.get(i);
            int quantity = orderQuantities.get(i);
            double itemTotalPrice = item.getPrice() * quantity;
            itemTotalPrices.add(itemTotalPrice);
            totalPrice += itemTotalPrice;
        }
    }

    @GetMapping("/orderSummary")
    public String showOrderSummary(Model model) {
        
        calculateOrderSummary();
        model.addAttribute("orderedItems", orderedItems);
        model.addAttribute("orderQuantities", orderQuantities);
        model.addAttribute("itemTotalPrices", itemTotalPrices);
        model.addAttribute("totalPrice", totalPrice);

        return "order_summary";
    }

    @GetMapping("/bill")
    public String payment(Model model, HttpSession session) 
    {
        calculateOrderSummary();
        session.setAttribute("totalPrice", totalPrice);
        String bookedTables = (String)session.getAttribute("bookedTables");
        String[] tableTokens = bookedTables.split(", ");
        for (String table : tableTokens)
        {
            bookservice.updateCustomerIdForTable(Integer.parseInt(table), 0);
        }


        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("orderedItems", orderedItems);
        model.addAttribute("orderQuantities", orderQuantities);
        model.addAttribute("itemTotalPrices", itemTotalPrices);
        model.addAttribute("totalPrice", totalPrice);
        return "bill";
    }
}
