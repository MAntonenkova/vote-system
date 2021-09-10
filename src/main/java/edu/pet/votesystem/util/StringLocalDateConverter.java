package edu.pet.votesystem.util;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringLocalDateConverter extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String s) {
        if (s == null || s.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(LocalDateStringConverter.DATE_TIME_FORMAT));
    }
}
