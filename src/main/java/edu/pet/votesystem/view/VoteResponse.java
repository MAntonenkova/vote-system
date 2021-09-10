package edu.pet.votesystem.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.pet.votesystem.util.LocalDateStringConverter;
import edu.pet.votesystem.util.StringLocalDateConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VoteResponse {

    // 1 успешно проголосовал или уже поздно
    // 2 количество голосов за этот ресторан за сегодня
    // 3 модель Vote

    private Boolean success;
    private Integer votesCountToday;
    private String restaurantName;
    @JsonSerialize(converter = LocalDateStringConverter.class)
    @JsonDeserialize(converter = StringLocalDateConverter.class)
    private LocalDateTime dateTime;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getVotesCountToday() {
        return votesCountToday;
    }

    public void setVotesCountToday(Integer votesCountToday) {
        this.votesCountToday = votesCountToday;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
