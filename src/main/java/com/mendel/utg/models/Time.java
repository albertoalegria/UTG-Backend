package com.mendel.utg.models;

import com.mendel.utg.utils.enums.Day;
import com.mendel.utg.utils.enums.Shift;

import javax.persistence.Embeddable;

import static com.mendel.utg.utils.Global.*;

/**
 * @author Alberto Alegria
 */

@Embeddable
public class Time {
    private int id;
    private int hour;
    private int hourOfDay;
    private int fancyHourOfDay;
    private Day day;
    private Shift shift;

    public Time(int hour) {
        this.id = hour - 1;
        this.hour = hour;
        hourOfDay = hour % HOURS_PER_DAY;

        if (hourOfDay == 0) {
            hourOfDay = HOURS_PER_DAY;
        }

        fancyHourOfDay = (FIRST_HOUR - 1) + hourOfDay;
        day = Day.getDay(((hour - 1) / 14) + 1);
        shift = hourOfDay <= SHIFT_HOUR ? Shift.MORNING : Shift.AFTERNOON;
    }

    public boolean isInSameDay(Time time) {
        return this.day.equals(time.getDay());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getFancyHourOfDay() {
        return fancyHourOfDay;
    }

    public void setFancyHourOfDay(int fancyHourOfDay) {
        this.fancyHourOfDay = fancyHourOfDay;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj instanceof Time &&
                id == ((Time) obj).getId() &&
                hour == ((Time) obj).getHour() &&
                hourOfDay == ((Time) obj).getHourOfDay() &&
                fancyHourOfDay == ((Time) obj).getFancyHourOfDay();

    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                "hour" + hour +
                "hourOfDay" + hourOfDay +
                "fancyHourOfDay" + fancyHourOfDay +
                "day" + day +
                "Shift" + shift +
                "}";
    }
}
