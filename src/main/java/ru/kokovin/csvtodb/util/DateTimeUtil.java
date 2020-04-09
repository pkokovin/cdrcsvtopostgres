package ru.kokovin.csvtodb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final Logger log = LoggerFactory.getLogger(DateTimeUtil.class);
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static LocalDateTime convert(String date) {
        log.info("trying convert to date string : " + date);
        String[] parts = date.split(" ");
        StringBuilder builder = new StringBuilder();
        LocalDateTime result = LocalDateTime.of(LocalDate.parse(parts[0]), LocalTime.parse(parts[1]));
        return result;
    }
}
