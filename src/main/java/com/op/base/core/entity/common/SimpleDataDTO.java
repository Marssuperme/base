package com.op.base.core.entity.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用简单数据类型,类型根据业务而定, 通常用于包裹Boolean, Integer, Float等简单的数据类型，方便前端
 * 使用JSON来解析，不可用于POJO。
 *
 * @author Du Ping
 * @date 2018-03-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleDataDTO<T> {

    T data;
}
