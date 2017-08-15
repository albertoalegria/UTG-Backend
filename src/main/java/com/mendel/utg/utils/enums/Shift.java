package com.mendel.utg.utils.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import static com.mendel.utg.utils.ResourceManager.MESSAGES;

/**
 * @author Alberto Alegria
 */
public enum Shift {
    MORNING(MESSAGES.getString("com.mendel.utg.utils.enums.shift.morning")),
    AFTERNOON(MESSAGES.getString("com.mendel.utg.utils.enums.shift.afternoon")),
    EXTRA_MORNING(MESSAGES.getString("com.mendel.utg.utils.enums.shift.extra-morning")),
    EXTRA_AFTERNOON(MESSAGES.getString("com.mendel.utg.utils.enums.shift.extra-afternoon"));

    private String name;

    Shift(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleName() {
        switch (this) {
            case MORNING:
                return "A";
            case AFTERNOON:
                return "B";
            case EXTRA_MORNING:
                return "C";
            case EXTRA_AFTERNOON:
                return "D";
        }
        return "UNDEFINED";
    }

    public static Shift normalize(Shift shift) {
        if (shift.equals(EXTRA_MORNING) || shift.equals(EXTRA_AFTERNOON)) {
            return shift.equals(EXTRA_MORNING) ? EXTRA_MORNING : EXTRA_AFTERNOON;
        }
        return shift;
    }
}
