package com.intermap.content.audit.entity;

import lombok.Data;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.entity
 * @Author：zouxiaodong
 * @Description: 响应公共实体类
 * @Date:Created in 18:13 2019/3/15.
 */
@Data
public class ResponseDto {

    /**
     * 接口返回值，200:正常；其他值：调用出错
     */
    private int code;

    /**
     * 结果说明，调用正常，返回ok,接口出错会返回错误描述
     */
    private String msg;

    /**
     * 接口返回结果，json 格式，详见每个接口的定义说明。
     */
    private Object result;
}
