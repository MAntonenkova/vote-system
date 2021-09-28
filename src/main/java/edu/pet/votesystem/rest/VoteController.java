package edu.pet.votesystem.rest;

import edu.pet.votesystem.service.VoteService;
import edu.pet.votesystem.util.Result;
import edu.pet.votesystem.view.DateRequest;
import edu.pet.votesystem.view.VotesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class VoteController {

    @Autowired
    VoteService service;

    @GetMapping(path = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public List<VotesResponse> getVotingResult() {
        LocalDate today = LocalDate.now();
        return service.getVotes(today);
    }

    @PostMapping(path = "/votes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public List<VotesResponse> getVotingResult(@RequestBody DateRequest request) {
        return service.getVotes(request.getVoteDate());
    }

    @GetMapping(path = "/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('vote')")
    public Result vote(@RequestParam("restId") Long restId){
        return service.vote(restId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
