package edu.pet.votesystem.repository;

import edu.pet.votesystem.model.Dish;
import edu.pet.votesystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT dish FROM Dish dish JOIN Restaurant rest ON dish.restaurant.restaurantId = rest.restaurantId" +
            " WHERE lower(rest.restaurantName)  = lower(:restaurantName) ")
    List<Dish> findDishes(@Param("restaurantName") String restaurantName);
}
