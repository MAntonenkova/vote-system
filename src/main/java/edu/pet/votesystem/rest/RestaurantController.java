package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.RestaurantService;
import edu.pet.votesystem.util.Result;
import edu.pet.votesystem.view.DishRequest;
import edu.pet.votesystem.view.RestaurantsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {


    @Autowired
    RestaurantService service;

    //localhost:8080/votesystem/restaurants
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('read')")
  //  @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<RestaurantsResponse> getAllRestaurants() {
        return service.getAllRestaurants();
    }

    //localhost:8080/votesystem/restaurants/3
    @GetMapping(path = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('read')")
   // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public RestaurantsResponse getRestaurant(@PathVariable Integer restaurantId) {
        return service.getRestaurant(restaurantId);
    }

    //localhost:8080/votesystem/restaurants/add?name=Björn
    @GetMapping("/add")
    @Secured("ROLE_ADMIN")
    public Result addRestaurant(@NotBlank(message = "restaurant name should not be empty")
                                @Size(min = 2, max = 100, message = "restaurant name should be between 2 and 100 characters")
                                @RequestParam("name") String name) {
        return service.updateRestaurant(null, name);
    }

    //localhost:8080/votesystem/restaurants/delete/7
    @DeleteMapping(path = "delete/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Result deleteRestaurant(@PathVariable Integer restaurantId) {
        return service.deleteRestaurant(restaurantId);
    }

    //localhost:8080/votesystem/restaurants/edit/3?name=Ugolek
    @PutMapping("/edit/{restaurantId}")
    @Secured("ROLE_ADMIN")
    public Result editRestaurant(@PathVariable Integer restaurantId,
                                 @Valid
                                 @NotBlank(message = "restaurant name should not be empty")
                                 @Size(min = 2, max = 100, message = "restaurant name should be between 2 and 100 characters")
                                 @RequestParam("name")
                                         String name) {
        return service.updateRestaurant(restaurantId, name);
    }

    // localhost:8080/votesystem/restaurants/8/addDish
/*  {
       "dishName":"Cottage cheese ring",
             "dishPrice": "390"
    }*/
    @PostMapping(path = "/{restaurantId}/addDish", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public RestaurantsResponse addDish(@PathVariable Integer restaurantId, @RequestBody DishRequest request) {
        service.addDish(restaurantId, request);
        return getRestaurant(restaurantId);
    }

    //localhost:8080/votesystem/restaurants/9/dish/12/edit
/*    {
        "dishName":"Bonbonss",
            "dishPrice": "305"
    }*/
    @PutMapping(path = "/{restaurantId}/dish/{dishId}/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public RestaurantsResponse editDish(@PathVariable Integer restaurantId, @PathVariable Integer dishId, @RequestBody DishRequest request) {
        service.editDish(restaurantId, dishId, request);
        return getRestaurant(restaurantId);
    }

    //localhost:8080/votesystem/restaurants/dish/12/delete
    @DeleteMapping(path = "/dish/{dishId}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Result deleteDish(@PathVariable Integer dishId) {
        return service.deleteDish(dishId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
