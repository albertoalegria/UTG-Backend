package com.mendel.utg.controllers;

import com.mendel.utg.utils.validation.MessageDTO;
import com.mendel.utg.utils.validation.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * @author Alberto Alegria
 */

@ControllerAdvice
public class ValidationHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private List<MessageDTO> processValidationError(ConstraintViolationException exception) {
        List<MessageDTO> messages = new ArrayList<>();
        ResourceBundle bundle = ResourceBundle.getBundle("ValidationMessages", Locale.US);
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            messages.add(new MessageDTO(bundle.getString(violation.getMessageTemplate()), MessageType.ERROR));
        }
        return messages;
    }
}
