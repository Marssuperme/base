package com.op.base.core.service.exception;
/**
 * NoSuchDataException
 *
 * @author Du Ping
 * @date 2018-11-04
 */
public class NoSuchDataException extends Exception {

    public NoSuchDataException(Integer id) {
        super(String.format("没有找到对应的数据(%s)！", id));
    }
}
