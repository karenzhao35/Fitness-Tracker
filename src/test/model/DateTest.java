package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {
    private Date testDate1;
    private Date testDate2;
    private String d1;
    private String d2;

    @BeforeEach
    void runBefore() {
        d1 = "2019-06-20";
        d2 = "2020-08-12";
        testDate1 = new Date(d1);
        testDate2 = new Date(d2);
    }

    @Test
    void testConstructor() {
        assertEquals(d1, testDate1.getDate());
        assertEquals(d2, testDate2.getDate());
    }
}
