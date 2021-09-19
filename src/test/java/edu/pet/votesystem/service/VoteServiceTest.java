package edu.pet.votesystem.service;

import edu.pet.votesystem.util.Result;
import edu.pet.votesystem.view.VotesResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring/springContext.xml"})
public class VoteServiceTest {

    @Autowired
    VoteService service;

    @Test
    public void getVotes(){
        LocalDate localDate = LocalDate.of(2021, 9,13);
        List<VotesResponse> votes = service.getVotes(LocalDate.now());
        String restaurantName = votes.get(1).getRestaurantName();
        int counts = votes.get(1).getVotesCountToday();
        Assert.assertEquals("Odessa mama", restaurantName);
        Assert.assertEquals(5, counts);
    }

    @Test
    public void vote(){
        Assert.assertEquals(Result.SUCCESS, service.vote(2, 3));
    }
}
