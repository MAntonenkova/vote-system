package edu.pet.votesystem.view;

public class VotesResponse {

    private String restaurantName;
    private Long votesCountToday;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getVotesCountToday() {
        return votesCountToday;
    }

    public void setVotesCountToday(Long votesCountToday) {
        this.votesCountToday = votesCountToday;
    }
}
