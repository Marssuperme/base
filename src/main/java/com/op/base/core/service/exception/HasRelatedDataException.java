package com.op.base.core.service.exception;

/**
 * HasRelatedDataException
 *
 * @author Du Ping
 * @date 2018-11-29
 */
public class HasRelatedDataException extends RuntimeException {
    public HasRelatedDataException(String id) {
        super(String.format("存在关联数据(%s)！", id));
    }
}
