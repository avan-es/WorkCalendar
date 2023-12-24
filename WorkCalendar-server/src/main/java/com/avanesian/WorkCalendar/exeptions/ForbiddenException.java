package com.avanesian.WorkCalendar.exeptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(final String message) {
        super(message);
    }
}
