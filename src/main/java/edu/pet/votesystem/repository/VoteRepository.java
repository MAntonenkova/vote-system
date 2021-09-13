package edu.pet.votesystem.repository;

import edu.pet.votesystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {


/*    // TODO: написать запрос -  Query to get all votes for the restaurant for the day
    @Query("SELECT vote FROM Vote vote JOIN Restaurant rest ON vote.restaurant.restaurantId = rest.restaurantId " +
            "WHERE  vote.voteDate = :voteDate AND lower(rest.restaurantName)  = lower(:restaurantName) ")
     List<Vote> getVotes(@Param("restaurantName") String restaurantName, @Param("voteDate") LocalDate voteDate);*/

    @Query("SELECT  vote FROM Vote vote WHERE vote.voteDate =:localDate")
    List<Vote> getVotesForToday(@Param("localDate")LocalDate localDate);
}
