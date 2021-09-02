package edu.pet.votesystem.view;

public class VoteResponse {

    // 1 успешно проголосовал или уже поздно
    // 2 количество голосов за этот ресторан за сегодня
    // 3 модель Vote

    private Boolean success;
    private Integer votesCountToday;

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
}
