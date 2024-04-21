package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepository;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenuItems() 
    {
        return menuRepository.findAll();
    }

    public Menu getMenuItemById(Integer itemId) 
    {
        return menuRepository.findById(itemId).orElse(null);
    }
}
