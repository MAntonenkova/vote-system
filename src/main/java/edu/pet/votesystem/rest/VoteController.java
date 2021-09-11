package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.VoteService;
import edu.pet.votesystem.view.VoteRequest;
import edu.pet.votesystem.view.VoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    VoteService service;

    //http://localhost:8080/votesystem/votes

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VoteResponse getVotingResult(@RequestBody VoteRequest request) {
        return service.getVoteCount(request);
        // TODO: проверить, то ли возвращает контроллер
    }

/*    {
        "restaurantName": "Odessa mama",
            "voteDateTime": "03.09.2021 : 15.00",
            "userName" : "Alice"
    }*/


}
