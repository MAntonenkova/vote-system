package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Dish;
import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.util.Result;
import edu.pet.votesystem.repository.DishRepository;
import edu.pet.votesystem.repository.RestaurantRepository;
import edu.pet.votesystem.view.*;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static edu.pet.votesystem.util.RestaurantUtil.putDishesToDishResponse;

@Service
public class RestaurantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    RestaurantRepository repository;

    @Autowired
    DishRepository dishRepository;

    //delete
    @Transactional
    public RestaurantResponse getRestaurantInfo(RestaurantRequest request) {
        List<Dish> dishes = repository.findDishes(request.getRestaurantName());

        if (dishes.isEmpty()) {
            return new RestaurantResponse();
        }

        RestaurantResponse response = new RestaurantResponse();
        response.setSuccess(true);
        response.setDishes(dishes);
        return response;
    }

    // find all restaurants
    @Transactional(readOnly = true)
    public List<RestaurantsResponse> getAllRestaurants() {
        LOGGER.info("Get all restaurants with it's menu");
        List<Restaurant> restaurants = repository.getAllRestaurants();
        List<RestaurantsResponse> responseList = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantsResponse response = new RestaurantsResponse();
            String restName = restaurant.getRestaurantName();
            response.setRestName(restName);
            List<Dish> dishes = restaurant.getDishes();
            List<DishResponse> dishResponses = putDishesToDishResponse(dishes);

            response.setDishes(dishResponses);
            responseList.add(response);
        }
        return responseList;
    }

    //find restaurant by id
    @Transactional(readOnly = true)
    public RestaurantsResponse getRestaurant(Integer id) {
        RestaurantsResponse response = new RestaurantsResponse();
        LOGGER.info("Get restaurant with id = {}", id);
        Optional<Restaurant> fob = repository.findById(id);
        if (fob.isEmpty()) {
            return null;
        }
        Restaurant restaurant = fob.get();
        response.setRestName(restaurant.getRestaurantName());

        List<Dish> dishes = restaurant.getDishes();
        if (dishes == null || dishes.isEmpty()) {
            return response;
        }
        List<DishResponse> dishResponses = putDishesToDishResponse(dishes);
        response.setDishes(dishResponses);
        return response;
    }

    //update restaurant's name or add new restaurant
    @Transactional
    public RestaurantsResponse updateRestaurant(Integer id, String name) {
        if (id != null) {
            LOGGER.info("Edit restaurant with name = {}", id);
            repository.updateName(id, name);
        } else {
            LOGGER.info("Add new restaurant with name = {}", name);
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(name);
            restaurant = repository.save(restaurant);
            id = (restaurant).getRestaurantId();
        }

        return getRestaurant(id);
    }

    //delete restaurant by id
    @Transactional
    public Result deleteRestaurant(int id) {
        if (repository.findById(id).isEmpty()) {
            return Result.NO_SUCH_ENTRY_EXIST;
        }
        LOGGER.info("Delete restaurant with id = {}", id);
        repository.deleteById(id);
        Optional<Restaurant> object = repository.findById(id);
        if (object.isEmpty()) {
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    // add new dish
    @Transactional
    public Result addDish(Integer restId, DishRequest request) {
        LOGGER.info("Add dish with name = {} and price = {} to restaurant with id = {}", request.getDishName(), request.getDishPrice(), restId);
        Dish dish = new Dish();
        dish.setDishName(request.getDishName());
        dish.setPrice(request.getDishPrice());
        Restaurant restaurant = repository.getOne(restId);
        dish.setRestaurant(restaurant);

        Integer dishId = (dishRepository.save(dish)).getDishId();
        if (dishId > 0) {
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    // edit dish
    @Transactional
    public Result editDish(Integer restId, Integer dishId, DishRequest request){
        LOGGER.info("Edit dish with id = {} and rest_id = {}", dishId, restId);
        Dish dish = dishRepository.getOne(dishId);
        if (dish == null){
           return Result.FAIL;
        }
        dishRepository.updateDish(request.getDishName(), request.getDishPrice(), dishId);
        return Result.SUCCESS;
    }

    //delete dish
    @Transactional
    public Result deleteDish(Integer dishId){
        if (dishRepository.findById(dishId).isEmpty()) {
            return Result.NO_SUCH_ENTRY_EXIST;
        }
        dishRepository.deleteById(dishId);
        return Result.SUCCESS;
    }

    // ------------
    @Transactional
    public List<Restaurant> findRestaurants() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Dish> findDishes() {
        return dishRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Dish getDish(Integer dishId) {
        Optional<Dish> fop = dishRepository.findById(dishId);
        Dish dish = fop.get();
        Hibernate.initialize(dish.getRestaurant());
        return dish;
    }

    @Transactional(readOnly = true)
    public List<Restaurant> findFullRestaurants() {
        List<Restaurant> fillRestaurantList = repository.findFullRestaurantList();
        return fillRestaurantList;
    }
}
