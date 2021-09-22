package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Dish;
import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.repository.DishRepository;
import edu.pet.votesystem.repository.RestaurantRepository;
import edu.pet.votesystem.util.Result;
import edu.pet.votesystem.view.DishRequest;
import edu.pet.votesystem.view.DishResponse;
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

    @Transactional
    public Result updateRestaurant(Integer id, String name) {
        if (id != null) {
            LOGGER.info("Edit restaurant with name = {}", id);
            repository.updateName(id, name);
        } else {

            LOGGER.info("Add new restaurant with name = {}", name);
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(name);
            try {
                repository.save(restaurant);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return Result.SUCCESS;
    }

    @Transactional
    public Result deleteRestaurant(int id) {
        LOGGER.info("Delete restaurant with id = {}", id);
        return repository.delete(id) != 0 ? Result.SUCCESS : Result.NO_SUCH_ENTRY_EXIST;
    }

    @Transactional
    public Result addDish(Integer restId, DishRequest request) {
        LOGGER.info("Add dish with name = {} and price = {} to restaurant with id = {}", request.getDishName(), request.getDishPrice(), restId);
        Dish dish = new Dish();
        dish.setDishName(request.getDishName());
        dish.setPrice(request.getDishPrice());
        Optional<Restaurant> fob = repository.findById(restId);
        if (fob.isEmpty()) {
            LOGGER.error("Restaurant with id = {} doesn't exist", restId);
            return Result.FAIL;
        }
        Restaurant restFromDb = fob.get();

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(restFromDb.getRestaurantId());
        restaurant.setRestaurantName(restFromDb.getRestaurantName());

        dish.setRestaurant(restaurant);

        Integer dishId = (dishRepository.save(dish)).getDishId();
        if (dishId > 0) {
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @Transactional
    public Result editDish(Integer restId, Integer dishId, DishRequest request) {
        LOGGER.info("Edit dish with id = {} and rest_id = {}", dishId, restId);
        Dish dish = dishRepository.getOne(dishId);
        if (dish == null) {
            return Result.FAIL;
        }
        dishRepository.updateDish(request.getDishName(), request.getDishPrice(), dishId);
        return Result.SUCCESS;
    }

    @Transactional
    public Result deleteDish(Integer dishId) {
        if (dishRepository.findById(dishId).isEmpty()) {
            return Result.NO_SUCH_ENTRY_EXIST;
        }
        dishRepository.deleteById(dishId);
        return Result.SUCCESS;
    }

    @Transactional(readOnly = true)
    public Dish getDish(Integer dishId) {
        Optional<Dish> fop = dishRepository.findById(dishId);
        if (fop.isEmpty()) {
            return null;
        }
        Dish dish = fop.get();
        Hibernate.initialize(dish.getRestaurant());
        return dish;
    }
}
