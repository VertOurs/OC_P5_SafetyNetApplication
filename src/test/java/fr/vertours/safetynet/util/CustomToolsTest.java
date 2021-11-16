package fr.vertours.safetynet.util;


import org.junit.jupiter.api.Test;


import java.time.*;

import static fr.vertours.safetynet.util.CustomTools.calculateAgewithLocalDate;
import static org.junit.jupiter.api.Assertions.*;



class CustomToolsTest {

    @Test
    void calculateAgewithLocalDateTest() {
        LocalDate date = LocalDate.of(1985,05,05);
        int expectedNumber = Math.abs(Period.between(LocalDate.now(ZoneId.systemDefault()), date).getYears());
        assertEquals(expectedNumber,calculateAgewithLocalDate(date));
    }
}