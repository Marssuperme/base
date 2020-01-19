package com.op.base.core.service.exception;

/**
 * 字典中存在相同的Code时抛出异常
 *
 * @author Chen Ze Jia
 * @date 2019-1-19
 */
public class AlreadyExistsCodeException extends RuntimeException {
    public AlreadyExistsCodeException() {
        super("字典中存在相同的Code");
    }
}
