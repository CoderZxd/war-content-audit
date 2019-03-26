package com.intermap.content.audit.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.utils
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 18:18 2019/3/25.
 */
public class ConstantUtil {

    /**
     * data_record表名前缀
     */
    public static String TABLE_NAME_PREFIX = "data_record_";

    /**
     * Data_record表中代表机器审核不确定的状态
     */
    public static Integer SYS_STATUS = 1;

    /**
     * TODO:NOT_WAITTING_AUTID_STATUS状态需要根据实际情况修改
     * 等待被人工审核状态
     * 1   - 审核通过
     * 2   - 审核不通过
     * 101 - 待审核
     */
    public static List<Integer> NOT_WAITTING_AUTID_STATUS = Arrays.asList(1,2,101);

    /**
     * TODO:需要根据实际情况修改
     * 101 - 待审核
     */
    public static Integer WAITTING_AUTID_STATUS = 101;

    /**
     * TODO:需要根据实际情况修改
     * 1 - 审核通过
     */
    public static Integer PASS_STATUS = 1;

    /**
     * TODO:需要根据实际情况修改
     * 2 - 审核不通过
     */
    public static Integer FAIL_STATUS = 2;

    /**
     * Data_record表每次获取的num数
     */
    public static Integer LIMITNUM = 20;
}
