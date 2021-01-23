package com.klasevich.lb.exception;

public class TerminalException extends Exception {
    public TerminalException() {
    }

    public TerminalException(String message) {
        super(message);
    }

    public TerminalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TerminalException(Throwable cause) {
        super(cause);
    }
}
