package edu.pet.votesystem.rest;

import edu.pet.votesystem.util.Result;
import edu.pet.votesystem.service.RestaurantService;
import edu.pet.votesystem.view.DishRequest;
import edu.pet.votesystem.view.RestaurantRequest;
import edu.pet.votesystem.view.RestaurantResponse;
import edu.pet.votesystem.view.RestaurantsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class RestaurantController {


    @Autowired
    RestaurantService service;

    //localhost:8080/votesystem/restaurants
    @GetMapping(path = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantsResponse> getAllRestaurants() {
        return service.getAllRestaurants();
    }

    //localhost:8080/votesystem/restaurant/3
    @GetMapping(path = "restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantsResponse getRestaurant(@PathVariable Integer restaurantId) {
        return service.getRestaurant(restaurantId);
    }

    //localhost:8080/votesystem/restaurant/add?name=Björn
    @GetMapping("/restaurant/add")
    public RestaurantsResponse addRestaurant(@RequestParam("name") String name) {
        return service.updateRestaurant(null, name);
    }

    //localhost:8080/votesystem/restaurant/delete/7
    @DeleteMapping(path = "/restaurant/delete/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteRestaurant(@PathVariable Integer restaurantId) {
        return service.deleteRestaurant(restaurantId);
    }

    //localhost:8080/votesystem/restaurant/edit/3?name=Ugolek
    @PutMapping("/restaurant/edit/{restaurantId}")
    public RestaurantsResponse editRestaurant(@PathVariable Integer restaurantId, @RequestParam("name") String name) {
        return service.updateRestaurant(restaurantId, name);
    }

    //localhost:8080/votesystem/restaurant/8/addDish
/*
    {
        "dishName":"Cottage cheese ring",
            "dishPrice": "390"
    }*/
    @PostMapping(path = "/restaurant/{restaurantId}/addDish", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantsResponse addDish(@PathVariable Integer restaurantId, @RequestBody DishRequest request) {
        service.addDish(restaurantId, request);
        return getRestaurant(restaurantId);
    }

    //localhost:8080/votesystem/restaurant/9/dish/12/edit
/*    {
        "dishName":"Bonbonss",
            "dishPrice": "305"
    }*/
    @PutMapping(path = "/restaurant/{restaurantId}/dish/{dishId}/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantsResponse editDish(@PathVariable Integer restaurantId, @PathVariable Integer dishId, @RequestBody DishRequest request) {
        service.editDish(restaurantId, dishId, request);
        return getRestaurant(restaurantId);
    }

    //localhost:8080/votesystem/restaurant/dish/12/delete
    @DeleteMapping(path = "/restaurant/dish/{dishId}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteDish(@PathVariable Integer dishId) {
        return service.deleteDish(dishId);
    }

    // ------------------
    @GetMapping("/check")
    public String checkAdmin() {
        return "Rest service is working";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RestaurantResponse getRestaurantInfo(@RequestBody RestaurantRequest request) {
        return service.getRestaurantInfo(request);
    }

    //http://localhost:8080/votesystem/restaurants/params/1
    @GetMapping("/params/{checkId}")
    public String checkParameters(@PathVariable("checkId") Integer id) {
        return id.toString();
    }


    // http://localhost:8080/votesystem/restaurants/params?comment=TEST
    @GetMapping("/params")
    public String checkParameters2(@RequestParam("comment") String comment) {
        return comment;
    }

}
