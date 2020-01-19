package com.op.base.core.service.exception;

/**
 * 字典值中存在相同的key时抛出异常
 *
 * @author Chen Ze Jia
 * @date 2019-1-19
 */
public class AlreadyExistsKeyException extends RuntimeException {
    public AlreadyExistsKeyException(String id) {
        super(String.format("字典(%s)的值存在相同的key", id));
    }
}
