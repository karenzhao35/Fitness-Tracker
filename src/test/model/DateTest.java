package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {
    private Date testDate1;
    private Date testDate2;
    private Date testDate3;
    private String d1;
    private String d2;
    private long current;

    @BeforeEach
    void runBefore() {
        d1 = "2019-06-20";
        d2 = "2020-08-12";
        testDate1 = new Date(d1);
        testDate2 = new Date(d2);
        testDate3 = new Date();
        current = System.currentTimeMillis();
    }

    @Test
    void testConstructor() {
        assertEquals(d1, testDate1.getDate());
        assertEquals(d2, testDate2.getDate());
        assertEquals(new java.sql.Date(current).toString(), testDate3.getDate());
    }
}
