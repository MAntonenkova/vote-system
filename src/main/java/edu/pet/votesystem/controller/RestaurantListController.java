package edu.pet.votesystem.controller;

import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(path = "/restaurantss")
public class RestaurantListController {

    @Autowired
    RestaurantService service;

    //http://localhost:8080/votesystem/restaurants
    @GetMapping
    public String findRestaurants(Model model) {
        List<Restaurant> restaurants = service.findRestaurants();
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        model.addAttribute("restaurants", restaurants);

        return "restaurantList";
    }
}
