package edu.pet.votesystem.repository;

import edu.pet.votesystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {


    // TODO: написать запрос -  Query to get all votes for the restaurant for the day
     List<Vote> getVotes(@Param("restaurantName") String restaurantName, @Param("localDateTime")LocalDateTime localDateTime);
}
