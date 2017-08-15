package com.mendel.utg.utils.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import static com.mendel.utg.utils.ResourceManager.MESSAGES;

/**
 * @author Alberto Alegria
 */

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Type {
    CLASSROOM(MESSAGES.getString("com.mendel.utg.utils.enums.type.classroom")),
    LABORATORY(MESSAGES.getString("com.mendel.utg.utils.enums.type.laboratory"));

    private String name;

    Type(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
