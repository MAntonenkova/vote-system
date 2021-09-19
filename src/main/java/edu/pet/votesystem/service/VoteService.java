package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.model.User;
import edu.pet.votesystem.model.Vote;
import edu.pet.votesystem.repository.RestaurantRepository;
import edu.pet.votesystem.repository.UserRepository;
import edu.pet.votesystem.repository.VoteRepository;
import edu.pet.votesystem.util.Result;
import edu.pet.votesystem.view.VotesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class VoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteService.class);

    public static LocalTime LAST_TIME_FOR_REVOTING = LocalTime.of(10, 59, 59);

    @Autowired
    VoteRepository voteRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<VotesResponse> getVotes(LocalDate localDate) {
        LOGGER.info("Get all votes for {}}", localDate);
        List<VotesResponse> responses = new ArrayList<>();
        List<Vote> votesForToday = voteRepository.getVotesForToday(localDate);

        Map<Integer, Integer> voteCounts = countVotes(votesForToday);
        for (Map.Entry<Integer, Integer> entry : voteCounts.entrySet()) {
            VotesResponse voteResponse = getVoteResponse(entry);
            responses.add(voteResponse);
        }
        return responses;
    }

    @Transactional
    public Result vote(Integer restId, Integer userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();

        if (!userExist(userId)) {
            LOGGER.info("User with id = {} does not exist", userId);
            return Result.FAIL;
        }

        Vote voteDB = voteRepository.getVote(userId, localDate);
        if (voteDB != null && localTime.isAfter(LAST_TIME_FOR_REVOTING)) {
            return Result.YOU_HAVE_ALREADY_VOTED_TODAY;
        }

        Restaurant restaurant = restaurantFromDB(restId);
        if (restaurant == null) {
            LOGGER.info("Restaurant with id = {} does not exist", restId);
            return Result.FAIL;
        }

        if (voteDB != null && localTime.isBefore(LAST_TIME_FOR_REVOTING)) {
            Integer voteId = voteDB.getVoteId();
            LOGGER.info("Update vote for user with id = {}, restaurant id = {}", userId, restId);
            voteRepository.update(voteId, localTime, restId);
            return Result.SUCCESS;
        }

        Vote vote = new Vote();
        vote.setRestaurant(restaurant);
        vote.setUserId(userId);
        vote.setVoteDate(localDate);
        vote.setVoteTime(localTime);

        LOGGER.info("User with id = {} voting for restaurant with id = {}", userId, restId);
        voteRepository.save(vote);
        return Result.SUCCESS;
    }

    private Map<Integer, Integer> countVotes(List<Vote> votesForToday) {
        Map<Integer, Integer> voteCounts = new HashMap<>();
        for (Vote vote : votesForToday) {
            Integer restaurantId = vote.getRestaurant().getRestaurantId();
            if (voteCounts.containsKey(restaurantId)) {
                voteCounts.computeIfPresent(restaurantId, (key, val) -> val + 1);
            } else {
                voteCounts.put(restaurantId, 1);
            }
        }
        return voteCounts;
    }

    private VotesResponse getVoteResponse(Map.Entry<Integer, Integer> entry) {
        VotesResponse votesResponse = new VotesResponse();
        Optional<Restaurant> optional = restaurantRepository.findById(entry.getKey());
        if(optional.isEmpty()){
            return null;
        }
        String restName = optional.get().getRestaurantName();

        votesResponse.setRestaurantName(restName);
        votesResponse.setVotesCountToday(entry.getValue());
        return votesResponse;
    }


    private Restaurant restaurantFromDB(Integer restId) {
        Optional<Restaurant> restById = restaurantRepository.findById(restId);
        return restById.orElse(null);
    }

    private boolean userExist(Integer userId) {
        Optional<User> userById = userRepository.findById(userId);
        User user = userById.orElse(null);
        return user != null;
    }

}
