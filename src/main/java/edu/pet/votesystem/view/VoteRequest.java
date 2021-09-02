package edu.pet.votesystem.view;

import edu.pet.votesystem.util.LocalDateTimeAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

public class VoteRequest {
    private String restaurantName;
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime voteDateTime;
    // по идее дата - LocalDateTime.now();
    private String userName;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public LocalDateTime getVoteDateTime() {
        return voteDateTime;
    }

    public void setVoteDateTime(LocalDateTime voteDateTime) {
        this.voteDateTime = voteDateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
