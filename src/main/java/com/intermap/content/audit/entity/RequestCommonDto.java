package com.intermap.content.audit.entity;

import lombok.Data;

import java.util.Random;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.entity
 * @Author：zouxiaodong
 * @Description:请求公共字段类
 * @Date:Created in 18:24 2019/3/15.
 */

@Data
public class RequestCommonDto {

    /**
     *产品标识，由系统为您分配
     */
    private String appId;

    /**
     *请求的当前 UNIX 时间戳。
     */
    private Long timestamp = System.currentTimeMillis();

    /**
     *随机整数，防重放
     */
    private int nonce = new Random().nextInt();

    /**
     *请求签名，用来验证本次请求的合法性
     */
    private String signature;
}
