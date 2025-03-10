package uk.ac.ucl.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Written by Zhouzhou

// Very simple function to generate the current time.
// Ref:
// DateTimeFormatter in Java standard lib
// https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html

public class createTimeStamp {
    public static String ISO() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return now.format(formatter);
    }
}
