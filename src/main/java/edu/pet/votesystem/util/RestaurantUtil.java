package edu.pet.votesystem.util;

import edu.pet.votesystem.model.Dish;
import edu.pet.votesystem.view.DishResponse;

import java.util.ArrayList;
import java.util.List;

public class RestaurantUtil {
    public static List<DishResponse> putDishesToDishResponse(List<Dish> dishes){
        List<DishResponse> dishResponses = new ArrayList<>();
        for (Dish dish : dishes) {
            DishResponse dishResponse = new DishResponse();
            dishResponse.setDishName(dish.getDishName());
            dishResponse.setDishPrice(dish.getPrice());
            dishResponses.add(dishResponse);
        }
        return dishResponses;
    }
}
