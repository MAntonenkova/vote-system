package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.RestaurantService;
import edu.pet.votesystem.view.RestaurantRequest;
import edu.pet.votesystem.view.RestaurantResponse;
import edu.pet.votesystem.view.RestaurantsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class RestaurantController {


    @Autowired
    RestaurantService service;

    //http://localhost:8080/votesystem/restaurants
    @GetMapping(path = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantsResponse> getAllRestaurants() {
        return service.getAllRestaurants();
    }

    //http://localhost:8080/votesystem/restaurant/3
    @GetMapping(path = "restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantsResponse getRestaurant(@PathVariable Integer restaurantId) {
        return service.getRestaurant(restaurantId);
    }

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
//TODO: request correct
 /*   {
        "restaurantName": "Odessa mama",
            "dishName": "Sorrel soup with egg",
            "price" : "390"
    }*/

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
