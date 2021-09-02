package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Dish;
import edu.pet.votesystem.repository.RestaurantRepository;
import edu.pet.votesystem.view.RestaurantRequest;
import edu.pet.votesystem.view.RestaurantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    RestaurantRepository repository;

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

}
