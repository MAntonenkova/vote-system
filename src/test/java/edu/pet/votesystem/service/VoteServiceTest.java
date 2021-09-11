package edu.pet.votesystem.service;

import edu.pet.votesystem.rest.VoteControllerOld;
import edu.pet.votesystem.view.VoteRequest;
import edu.pet.votesystem.view.VoteResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring/springContext.xml"})
public class VoteServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceTest.class);

    @Autowired
    VoteControllerOld controller;

    @Test
    public void voteInfo() {
        VoteRequest request = new VoteRequest();
        request.setRestaurantName("apartment 44");
        //  request.setVoteDate(LocalDate.now());
        request.setVoteDateTime(LocalDateTime.of(2021, 8, 28, 12, 30));

        VoteResponse response = controller.getVotingResult(request);
        int voteCount = response.getVotesCountToday();

        LOGGER.info("Vote's count for restaurant {} = {} for today", voteCount, response.getRestaurantName());
        Assert.assertEquals(5, voteCount);
    }
}
