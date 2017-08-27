package com.mendel.utg.utils.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import static com.mendel.utg.utils.ResourceManager.MESSAGES;

/**
 * @author Alberto Alegria
 */
public enum StudyLevel {
    BACHELOR(MESSAGES.getString("com.mendel.utg.utils.enums.study.bachelor")),
    MASTER(MESSAGES.getString("com.mendel.utg.utils.enums.study.master")),
    PHD(MESSAGES.getString("com.mendel.utg.utils.enums.study.phd"));

    private String name;

    StudyLevel(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public String getAbbr() {
        switch (this) {
            case BACHELOR:
                return MESSAGES.getString("com.mendel.utg.utils.enums.study.bachelor.abbr");
            case MASTER:
                return MESSAGES.getString("com.mendel.utg.utils.enums.study.master.abbr");
            case PHD:
                return MESSAGES.getString("com.mendel.utg.utils.enums.study.phd.abbr");
        }
        return "INVALID";
    }

    public void setName(String name) {
        this.name = name;
    }
}
