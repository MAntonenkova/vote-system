package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Dish;
import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.repository.DishRepository;
import edu.pet.votesystem.repository.RestaurantRepository;
import edu.pet.votesystem.view.RetaurantRequest;
import edu.pet.votesystem.view.RestaurantResponse;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    RestaurantRepository repository;

    @Autowired
    DishRepository dishRepository;

    @Transactional
    public RestaurantResponse getRestaurantInfo(RetaurantRequest request) {
        List<Dish> dishes = repository.findDishes(request.getRestaurantName());

        if (dishes.isEmpty()) {
            return new RestaurantResponse();
        }

        RestaurantResponse response = new RestaurantResponse();
        response.setSuccess(true);
        response.setDishes(dishes);
        return response;
    }

    @Transactional
    public List<Restaurant> findRestaurants() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Dish> findDishes(){
        return  dishRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Dish getDish(Integer dishId){
        Optional<Dish> fop = dishRepository.findById(dishId);
        Dish dish = fop.get();
        Hibernate.initialize(dish.getRestaurant());
        return dish;
    }

    @Transactional(readOnly = true)
    public List<Restaurant> findFullRestaurants(){
        List<Restaurant> fillRestaurantList = repository.findFullRestaurantList();
        return fillRestaurantList;
    }
}
