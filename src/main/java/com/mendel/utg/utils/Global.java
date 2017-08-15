package com.mendel.utg.utils;

import static com.mendel.utg.utils.ResourceManager.CONFIG;

/**
 * @author Alberto Alegria
 */
public class Global {
    public static int TOTAL_HOURS = Integer.parseInt(CONFIG.getString("mendel.config.totalHours"));
    public static int DAYS_PER_WEEK = Integer.parseInt(CONFIG.getString("mendel.config.daysPerWeek"));
    public static int HOURS_PER_DAY = Integer.parseInt(CONFIG.getString("mendel.config.hoursPerDay"));
    public static int FIRST_HOUR = Integer.parseInt(CONFIG.getString("mendel.config.firstHour"));
    public static int SHIFT_HOUR = Integer.parseInt(CONFIG.getString("mendel.config.shiftHour"));
    public static int MAX_SEMESTER = Integer.parseInt(CONFIG.getString("mendel.config.maxSemester"));
}
