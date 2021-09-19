package edu.pet.votesystem.repository;

import edu.pet.votesystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT  vote FROM Vote vote WHERE vote.voteDate =:localDate")
    List<Vote> getVotesForToday(@Param("localDate")LocalDate localDate);

    @Query ("SELECT vote FROM Vote vote WHERE vote.userId =:userId AND vote.voteDate =:localDate")
    Vote getVote(@Param ("userId") Integer userId, @Param("localDate") LocalDate localDate);

    @Modifying
    @Query ("UPDATE Vote vote SET vote.restaurant.restaurantId =:restId, vote.voteTime =:localTime WHERE vote.voteId =:voteId")
    void update(@Param ("voteId") Integer voteId, @Param("localTime")LocalTime localTime, @Param("restId") Integer restId);

}
