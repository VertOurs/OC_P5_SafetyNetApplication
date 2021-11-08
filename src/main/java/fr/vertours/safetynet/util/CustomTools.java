package fr.vertours.safetynet.util;

import java.time.LocalDate;
import java.time.Period;

public class CustomTools {

    public static int calculateAgewithLocalDate (LocalDate date) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(now, date);
        return Math.abs(period.getYears());
    }
}
