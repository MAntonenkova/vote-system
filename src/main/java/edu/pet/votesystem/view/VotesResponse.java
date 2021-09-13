package edu.pet.votesystem.view;

public class VotesResponse {

    private String restaurantName;
    private Integer votesCountToday;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getVotesCountToday() {
        return votesCountToday;
    }

    public void setVotesCountToday(Integer votesCountToday) {
        this.votesCountToday = votesCountToday;
    }
}
