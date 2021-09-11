package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Dish;
import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.repository.DishRepository;
import edu.pet.votesystem.repository.RestaurantRepository;
import edu.pet.votesystem.view.DishResponse;
import edu.pet.votesystem.view.RestaurantRequest;
import edu.pet.votesystem.view.RestaurantResponse;
import edu.pet.votesystem.view.RestaurantsResponse;
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
    @Transactional
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
    @Transactional
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
        List<DishResponse> dishResponses = putDishesToDishResponse(dishes);

        response.setDishes(dishResponses);
        return response;
    }

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
