package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.VoteService;
import edu.pet.votesystem.view.DateRequest;
import edu.pet.votesystem.view.VotesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VoteController {

    @Autowired
    VoteService service;

    //http://localhost:8080/votesystem/votes
    @GetMapping(path = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VotesResponse> getVotingResult() {
        LocalDate today = LocalDate.now();
        return service.getVotes(today);
    }

    //http://localhost:8080/votesystem/votes
/*    {
        "voteDate" : "13.09.2021"
    }*/
    @PostMapping(path = "/votes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VotesResponse> getVotingResult(@RequestBody DateRequest request) {
        return service.getVotes(request.getVoteDate());
    }

}
