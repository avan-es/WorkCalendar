package com.avanesian.WorkCalendar.exeptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }
}
