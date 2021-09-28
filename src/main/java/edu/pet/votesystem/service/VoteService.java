package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Restaurant;
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

        Map<Long, Long> voteCounts = countVotes(votesForToday);
        for (Map.Entry<Long, Long> entry : voteCounts.entrySet()) {
            VotesResponse voteResponse = getVoteResponse(entry);
            responses.add(voteResponse);
        }
        return responses;
    }

    @Transactional
    public Result vote(Long restId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();

        Long userId = ServiceUtil.getAuthUser(userRepository).getId();
        Vote voteDB = voteRepository.getVote(userId, localDate);

        if (voteDB != null) {
            if (localTime.isAfter(LAST_TIME_FOR_REVOTING)) {
                LOGGER.info("User {} has already voted today", userId);
                return Result.YOU_HAVE_ALREADY_VOTED_TODAY;
            }
            if (localTime.isBefore(LAST_TIME_FOR_REVOTING)) {
                Integer voteId = voteDB.getVoteId();
                LOGGER.info("Update vote for user with id = {}, restaurant id = {}", userId, restId);
                voteRepository.update(voteId, localTime, restId);
                return Result.SUCCESS;
            }
        }

        Restaurant restaurant = getRestaurantFromDB(restId);
        if (restaurant == null) {
            LOGGER.info("Restaurant with id = {} does not exist", restId);
            return Result.FAIL;
        }

        Vote vote = setVote(localDate, localTime, userId, restaurant);

        LOGGER.info("User with id = {} vote for restaurant with id = {}", userId, restId);
        voteRepository.save(vote);
        return Result.SUCCESS;
    }

    private Map<Long, Long> countVotes(List<Vote> votesForToday) {
        Map<Long, Long> voteCounts = new HashMap<>();
        for (Vote vote : votesForToday) {
            Long restaurantId = vote.getRestaurant().getRestaurantId();
            if (voteCounts.containsKey(restaurantId)) {
                voteCounts.computeIfPresent(restaurantId, (key, val) -> val + 1);
            } else {
                voteCounts.put(restaurantId, 1L);
            }
        }
        return voteCounts;
    }

    private VotesResponse getVoteResponse(Map.Entry<Long, Long> entry) {
        VotesResponse votesResponse = new VotesResponse();
        Optional<Restaurant> optional = restaurantRepository.findById(entry.getKey());
        if (optional.isEmpty()) {
            return null;
        }
        String restName = optional.get().getRestaurantName();

        votesResponse.setRestaurantName(restName);
        votesResponse.setVotesCountToday(entry.getValue());
        return votesResponse;
    }

    private Restaurant getRestaurantFromDB(Long restId) {
        Optional<Restaurant> restById = restaurantRepository.findById(restId);
        return restById.orElse(null);
    }

    private Vote setVote(LocalDate localDate, LocalTime localTime, Long userId, Restaurant restaurant) {
        Vote vote = new Vote();
        vote.setRestaurant(restaurant);
        vote.setUserId(userId);
        vote.setVoteDate(localDate);
        vote.setVoteTime(localTime);
        return vote;
    }
}
