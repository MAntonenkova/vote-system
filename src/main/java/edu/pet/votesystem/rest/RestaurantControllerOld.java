package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.RestaurantService;
import edu.pet.votesystem.view.RestaurantRequest;
import edu.pet.votesystem.view.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;

/*@Component
@Path("/restaurants")*/
public class RestaurantControllerOld {

  @Autowired
  RestaurantService service;

/*  @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)*/
    public RestaurantResponse getRestaurantInfo(RestaurantRequest request){
    return service.getRestaurantInfo(request);
  }
}
