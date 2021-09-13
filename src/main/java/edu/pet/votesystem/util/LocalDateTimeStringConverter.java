package edu.pet.votesystem.util;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeStringConverter extends StdConverter<LocalDateTime, String> {

    static final String DATE_TIME_FORMAT = "dd.MM.yyyy : HH.mm";

    @Override
    public String convert(LocalDateTime localDateTime) {
        return localDateTime == null ? "" : localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }
}
