package edu.pet.votesystem.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.pet.votesystem.util.LocalDateStringConverter;
import edu.pet.votesystem.util.StringLocalDateConverter;

import java.time.LocalDate;

public class DateRequest {

    @JsonSerialize(converter = LocalDateStringConverter.class)
    @JsonDeserialize(converter = StringLocalDateConverter.class)
    private LocalDate voteDate;

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }
}
