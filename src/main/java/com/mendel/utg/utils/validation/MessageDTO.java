package com.mendel.utg.utils.validation;

/**
 * @author Alberto Alegria
 */
public class MessageDTO {
    private String message;
    private MessageType type;

    public MessageDTO() {}

    public MessageDTO(String message, MessageType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
