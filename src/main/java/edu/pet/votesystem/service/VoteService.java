package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Vote;
import edu.pet.votesystem.repository.VoteRepository;
import edu.pet.votesystem.view.VoteRequest;
import edu.pet.votesystem.view.VoteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    VoteRepository repository;

    @Transactional
    public VoteResponse getVoteCount(VoteRequest request) {
        List<Vote> votes = repository.getVotes(request.getRestaurantName(), LocalDateTime.now());

        // TODO загрузить все, что нужно, в response


        VoteResponse response = new VoteResponse();
        return response;
    }
}
