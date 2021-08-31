package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository dao;

    public List<Restaurant> findRestaurants(){
        return null;
    }


}
