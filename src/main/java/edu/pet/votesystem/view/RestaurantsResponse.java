package edu.pet.votesystem.view;

import edu.pet.votesystem.model.Dish;

import java.util.List;

public class RestaurantsResponse {
    private String restName;
    private List<DishResponse> dishes;

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public List<DishResponse> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishResponse> dishes) {
        this.dishes = dishes;
    }
}
