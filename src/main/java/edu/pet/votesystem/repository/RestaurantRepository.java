package edu.pet.votesystem.repository;

import edu.pet.votesystem.model.Dish;
import edu.pet.votesystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

/*    @Query("SELECT dish FROM Dish dish JOIN Restaurant rest ON dish.restaurant.restaurantId = rest.restaurantId" +
            " WHERE lower(rest.restaurantName)  = lower(:restaurantName) ")
    List<Dish> findDishes(@Param("restaurantName") String restaurantName);


    @Query(name = "Restaurant.findAllWithDishes",
            value = "SELECT rest FROM Restaurant rest LEFT JOIN FETCH rest.dishes")
    List<Restaurant> findFullRestaurantList();*/

    @Query("SELECT DISTINCT rest FROM Restaurant rest JOIN Dish dish ON dish.restaurant.restaurantId = rest.restaurantId " +
            "WHERE dish IS NOT NULL")
    List<Restaurant> getAllRestaurants();

    @Modifying
    @Query("UPDATE Restaurant rest SET rest.restaurantName =:restName WHERE rest.restaurantId = :restId")
    void updateName(@Param("restId") Integer restId, @Param("restName") String restName);

    @Modifying
    @Query("DELETE FROM Restaurant  rest WHERE rest.restaurantId =:id")
    int delete(@Param("id") Integer id);

}
