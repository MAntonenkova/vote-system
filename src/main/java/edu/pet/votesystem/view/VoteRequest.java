package edu.pet.votesystem.view;

import edu.pet.votesystem.util.LocalDateTimeAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VoteRequest {
    private String restaurantName;
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDate voteDate;
    // по идее дата - LocalDateTime.now();
    private String userName;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
