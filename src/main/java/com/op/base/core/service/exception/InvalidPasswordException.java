package com.op.base.core.service.exception;

/**
 * InvalidPasswordException
 *
 * @author Du Ping
 * @date 2018-11-05
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
