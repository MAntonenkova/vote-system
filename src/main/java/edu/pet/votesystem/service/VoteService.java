package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.model.Vote;
import edu.pet.votesystem.repository.RestaurantRepository;
import edu.pet.votesystem.repository.VoteRepository;
import edu.pet.votesystem.view.VotesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class VoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    VoteRepository repository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Transactional
    public List<VotesResponse> getVotes(LocalDate localDate) {
        LOGGER.info("Get all votes for {}}", localDate);
        List<VotesResponse> responses = new ArrayList<>();
        List<Vote> votesForToday = repository.getVotesForToday(localDate);

        Map<Integer, Integer> voteCounts = countVotes(votesForToday);
        for (Map.Entry<Integer, Integer> entry : voteCounts.entrySet()) {
            VotesResponse voteResponse = getVoteResponse(entry);
            responses.add(voteResponse);
        }
        return responses;
    }

    private Map<Integer, Integer> countVotes(List<Vote> votesForToday) {
        Map<Integer, Integer> voteCounts = new HashMap<>();
        for (Vote vote : votesForToday) {
            Integer restaurantId = vote.getRestaurant().getRestaurantId();
            if (voteCounts.containsKey(restaurantId)) {
                voteCounts.computeIfPresent(restaurantId, (key, val) -> val+1);
            } else {
                voteCounts.put(restaurantId, 1);
            }
        }
        return voteCounts;
    }

    private VotesResponse getVoteResponse(Map.Entry<Integer, Integer> entry) {
        VotesResponse votesResponse = new VotesResponse();
        Optional<Restaurant> optional = restaurantRepository.findById(entry.getKey());
        String restName = optional.get().getRestaurantName();

        votesResponse.setRestaurantName(restName);
        votesResponse.setVotesCountToday(entry.getValue());
        return votesResponse;
    }

}
