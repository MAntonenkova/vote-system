package edu.pet.votesystem.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.pet.votesystem.util.LocalDateStringConverter;
import edu.pet.votesystem.util.StringLocalDateConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VoteRequest {
    private String restaurantName;
    // @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @JsonSerialize(converter = LocalDateStringConverter.class)
    @JsonDeserialize(converter = StringLocalDateConverter.class)
    // TODO: дату перевести из LocalDate в LocalDateTime
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
