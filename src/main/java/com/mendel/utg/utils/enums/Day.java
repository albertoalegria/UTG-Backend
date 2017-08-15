package com.mendel.utg.utils.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import static com.mendel.utg.utils.ResourceManager.MESSAGES;

/**
 * @author Alberto Alegria
 */
public enum Day {
    MONDAY(MESSAGES.getString("com.mendel.utg.utils.enums.day.monday")),
    TUESDAY(MESSAGES.getString("com.mendel.utg.utils.enums.day.tuesday")),
    WEDNESDAY(MESSAGES.getString("com.mendel.utg.utils.enums.day.wednesday")),
    THURSDAY(MESSAGES.getString("com.mendel.utg.utils.enums.day.thursday")),
    FRIDAY(MESSAGES.getString("com.mendel.utg.utils.enums.day.friday")),
    SATURDAY(MESSAGES.getString("com.mendel.utg.utils.enums.day.saturday")),
    SUNDAY(MESSAGES.getString("com.mendel.utg.utils.enums.day.sunday"));

    private String name;
    Day(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Day[] getWeek() {
        return new Day[] {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY};
    }

    public static Day getDay(int id) {
        switch (id) {
            case 1:
                return MONDAY;
            case 2:
                return TUESDAY;
            case 3:
                return WEDNESDAY;
            case 4:
                return THURSDAY;
            case 5:
                return FRIDAY;
            case 6:
                return SATURDAY;
            default:
                return SUNDAY;
        }
    }
}
