package ru.kokovin.csvtodb.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTimeUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static LocalDateTime convert(String date) {
        log.info("trying convert to date string : " + date);
        String[] parts = date.split(" ");
        StringBuilder builder = new StringBuilder();
        LocalDateTime result = LocalDateTime.of(LocalDate.parse(parts[0]), LocalTime.parse(parts[1]));
        return result;
    }
    public static LocalDateTime getBegin(Month month) {
        return LocalDateTime.of(LocalDate.of(LocalDate.now().getYear(), month, 1), LocalTime.MIN);
    }
    public static LocalDateTime getEnd(Month month) {
        return LocalDateTime.of(LocalDate.of(LocalDate.now().getYear(), month.plus(1), 1).minusDays(1), LocalTime.MAX);
    }

}
