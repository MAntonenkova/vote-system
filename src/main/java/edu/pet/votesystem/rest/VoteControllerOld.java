package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.VoteService;
import edu.pet.votesystem.view.VoteRequest;
import edu.pet.votesystem.view.VoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/vote")
public class VoteControllerOld {

    @Autowired
    VoteService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VoteResponse getVotingResult(VoteRequest request){
        return service.getVoteCount(request);
        // TODO: проверить, то ли возвращает контроллер
    }
}
