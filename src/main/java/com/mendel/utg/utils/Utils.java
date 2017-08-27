package com.mendel.utg.utils;

import com.mendel.utg.models.Time;
import com.mendel.utg.utils.enums.Day;
import com.mendel.utg.utils.enums.Shift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alberto Alegria
 */
public final class Utils {
    public static class Times {

        public static List<Time> getAllTimes() {
            List<Time> times = new ArrayList<>();

            for (int i = 1; i <= 70; i++) {
                times.add(new Time(i));
            }

            return times;
        }

        public static List<Time> getTimesArray(int first, int last) {
            return getAllTimes().stream().filter(time -> time.getHourOfDay() >= first && time.getHourOfDay() <= last)
                    .collect(Collectors.toList());
        }

        public static List<Time> getTimesForShift(Shift shift) {
            return getAllTimes().stream().filter(time -> time.getShift().equals(shift)).collect(Collectors.toList());
        }

        public static List<Time> getTimesForDay(Day day) {
            return getAllTimes().stream().filter(time -> time.getDay().equals(day)).collect(Collectors.toList());
        }

        public static List<Time> getTimesRange(int first, int last) {
            ArrayList<Time> times = new ArrayList<>();

            for (int i = first; i <= last; i++) {
                times.add(new Time(i));
            }

            return times;
        }
    }
}
