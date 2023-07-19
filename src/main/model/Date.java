package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
    private String date;

//    // EFFECTS: Constructs a date with the current date in format YYYY-MM-DD
//    public Date() {
//        this.date = today.toString();
//    }

    // REQUIRES: given date must be in format YYYY-MM-DD
    // EFFECTS: Constructs a date with the given date
    public Date(String date) {
        this.date = date;
    }

    // getters
    public String getDate() {
        return this.date;
    }


    // The following code is taken from Java T Point:
    // https://www.javatpoint.com/java-get-current-date#:~:text=Get%20Current%20Date%20%26%20Time%3A%20java.time.
    // LocalDateTime&text=of%20LocalDateTime%20class.-,If%20we%20print%20the%20instance%20of%20LocalDateTime%
    // 20class%2C%20it,the%20current%20date%20and%20time.

//    long millis = System.currentTimeMillis();
//    java.sql.Date today = new java.sql.Date(millis);


}
