package edu.pet.votesystem.service;

import edu.pet.votesystem.view.DishRequest;
import edu.pet.votesystem.view.RestaurantsResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring/springContext.xml"})
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void getAllRestaurants() {
        List<RestaurantsResponse> restaurants = service.getAllRestaurantsWithDishes();
        Assert.assertEquals(9, restaurants.size());
    }

    @Test
    public void getRestaurant() {
        RestaurantsResponse restaurant = service.getRestaurant(9L);
        Assert.assertEquals(3, restaurant.getDishes().size());
    }

    @Test
    public void addRestaurant() {
        int restCount = service.getTotalRestaurants().size();
        service.editRestaurant(null, "New restaurant");
        Assert.assertEquals(++restCount, service.getTotalRestaurants().size());
    }

    @Test
    public void editRestaurant() {
        String newName = "Mushrooms";
        service.editRestaurant(1L, newName);
        Assert.assertEquals(newName, service.getRestaurant(1L).getRestName());
    }

    @Test
    public void addDish() {
        DishRequest dishRequest = new DishRequest();
        dishRequest.setDishName("Cream cheese and smoked salmon");
        dishRequest.setDishPrice(750);
        service.saveDish(8L, dishRequest);
        Assert.assertEquals(2, service.getRestaurant(8L).getDishes().size());
    }

    @Test
    public void editDish() {
        DishRequest dishRequest = new DishRequest();
        dishRequest.setDishName("Bonbons");
        dishRequest.setDishPrice(300);
        service.editDish(9L, 12L, dishRequest);
        Assert.assertEquals("Bonbons", service.getDish(12L).getDishName());
    }

    @Test(expected = ConstraintViolationException.class)
    public void addDuplicateRestaurant() {
        service.editRestaurant(null, "Khachapuri");
    }

    @Test(expected = ValidationException.class)
    public void addEmptyRestaurantName(){
        service.editRestaurant(null, "");
    }

    @Test(expected = ValidationException.class)
    public void addDishWithEmptyName(){
        DishRequest dishRequest = new DishRequest();
        dishRequest.setDishName(null);
        dishRequest.setDishPrice(750);
        service.saveDish(8L, dishRequest);
    }


}