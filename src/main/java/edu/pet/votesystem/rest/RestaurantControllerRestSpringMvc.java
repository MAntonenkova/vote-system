package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.RestaurantService;
import edu.pet.votesystem.view.RestaurantRequest;
import edu.pet.votesystem.view.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantControllerRestSpringMvc {

    //http://localhost:8080/votesystem/restaurants

    @Autowired
    RestaurantService service;

    @GetMapping("/check")
    public String checkAdmin() {
        return "Rest service is working";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RestaurantResponse getRestaurantInfo(@RequestBody RestaurantRequest request){
        return service.getRestaurantInfo(request);
    }
//TODO: запрос
 /*   {
        "restaurantName": "Odessa mama",
            "dishName": "Sorrel soup with egg",
            "price" : "390"
    }*/

}
