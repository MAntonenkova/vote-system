package edu.pet.votesystem.view;

import edu.pet.votesystem.model.Dish;

import java.util.List;

public class RestaurantResponse {

    // блюдо успешно добавилось (или такое уже есть)
    // возвращать все блюда с ценами для этого ресторана
    // model Dish

    private Boolean success;
    private List<Dish> dishes;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
