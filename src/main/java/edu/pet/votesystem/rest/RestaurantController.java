package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.RestaurantService;
import edu.pet.votesystem.util.Result;
import edu.pet.votesystem.view.DishRequest;
import edu.pet.votesystem.view.RestaurantsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('read')")
    public List<RestaurantsResponse> getAllRestaurants() {
        return service.getAllRestaurantsWithDishes();
    }

    @GetMapping(path = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('read')")
    public RestaurantsResponse getRestaurant(@PathVariable Long restaurantId) {
        return service.getRestaurant(restaurantId);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('write')")
    public Result addRestaurant(@Valid
                                @RequestParam("name") String name) {
        return service.editRestaurant(null, name);
    }

    @DeleteMapping(path = "delete/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public Result deleteRestaurant(@PathVariable Integer restaurantId) {
        return service.deleteRestaurant(restaurantId);
    }

    @PutMapping("/edit/{restaurantId}")
    @PreAuthorize("hasAuthority('write')")
    public Result editRestaurant(@PathVariable Long restaurantId,
                                 @Valid
                                 @NotBlank(message = "restaurant name should not be empty")
                                 @Size(min = 2, max = 100, message = "restaurant name should be between 2 and 100 characters")
                                 @RequestParam("name") String name) {
        return service.editRestaurant(restaurantId, name);
    }

    @PostMapping(path = "/{restaurantId}/addDish", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public RestaurantsResponse addDish(@PathVariable Long restaurantId,@RequestBody DishRequest request) {
        service.saveDish(restaurantId, request);
        return getRestaurant(restaurantId);
    }

    @PutMapping(path = "/{restaurantId}/dish/{dishId}/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public RestaurantsResponse editDish(@PathVariable Long restaurantId, @PathVariable Long dishId, @RequestBody DishRequest request) {
        service.editDish(restaurantId, dishId, request);
        return getRestaurant(restaurantId);
    }

    @DeleteMapping(path = "/dish/{dishId}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public Result deleteDish(@PathVariable Long dishId) {
        return service.deleteDish(dishId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
