package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.RestaurantService;
import edu.pet.votesystem.view.RestaurantRequest;
import edu.pet.votesystem.view.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/restaurants")
public class RestaurantController {

  @Autowired
  RestaurantService service;

  @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantResponse getRestaurantInfo(RestaurantRequest request){
    return service.getRestaurantInfo(request);
  }
}
