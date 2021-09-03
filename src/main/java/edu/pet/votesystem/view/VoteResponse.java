package edu.pet.votesystem.view;

public class VoteResponse {

    // 1 успешно проголосовал или уже поздно
    // 2 количество голосов за этот ресторан за сегодня
    // 3 модель Vote

    private Boolean success;
    private Integer votesCountToday;
    private String restaurantName;

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
}
