package com.codegym.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        try {
            return LocalDateTime.parse(text);
        } catch (Exception e){
            return LocalDateTime.now();
        }
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return dateTimeFormatter.format(object);
    }
}
