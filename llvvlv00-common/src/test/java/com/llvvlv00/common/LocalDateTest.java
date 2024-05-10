package com.llvvlv00.common;

import org.junit.Test;

import java.time.LocalDate;

public class LocalDateTest {
    @Test
    public void testLocalDate() {
        LocalDate localDate = LocalDate.of(2018, 1, 1);
        LocalDate turnoverDate = LocalDate.of(2018, 1, 2);
        System.out.println("localDate.isAfter(turnoverDate) = " + localDate.isAfter(turnoverDate));
    }
}
