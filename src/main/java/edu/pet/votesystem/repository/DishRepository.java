package edu.pet.votesystem.repository;

import edu.pet.votesystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    // delete
    @Query("SELECT dish FROM Dish dish JOIN Restaurant rest ON dish.restaurant.restaurantId = rest.restaurantId " +
            "WHERE rest.restaurantName = :restName ")
    List<Dish> getAllDishes(@Param("restName") String restName);

    // update
    @Modifying
    @Query("UPDATE Dish dish SET dish.dishName =:dishName, dish.price =:dishPrice WHERE dish.dishId =:dishId")
    void updateDish(@Param("dishName") String dishName, @Param("dishPrice")Integer dishPrice, @Param("dishId") Integer dishId);
}
