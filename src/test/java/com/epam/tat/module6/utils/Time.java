package com.epam.tat.module6.utils;

import java.time.LocalDate;

public class Time {

    String actualMonth;
    String actualYear;

    public Time () {
        this.actualMonth = getActualMonth();
        this.actualYear = getActualYear();
    }

    public String getActualMonth() {
        return Integer.toString(LocalDate.now().getMonthValue());
    }

    public String getActualYear() {
        return Integer.toString(LocalDate.now().getYear());
    }
}
