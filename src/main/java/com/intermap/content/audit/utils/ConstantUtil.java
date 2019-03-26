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
     * TODO:需要根据实际情况修改
     * Data_record表中代表机器审核不确定的状态
     */
    public static Integer SYS_STATUS = 1;

    /**
     * TODO:需要根据实际情况修改
     * 设置待审核消息的状态为101，避免重复被审核
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
     * TODO:根据实际情况进行修改
     * Data_record表每次获取的num数
     */
    public static Integer LIMITNUM = 20;

    /**
     * TODO:EXCLUDE_STATUSES状态需要根据实际情况修改
     * 排除审核通过、审核不通过以及待审核的状态，用于过滤待审核状态的data record
     * 1   - 审核通过
     * 2   - 审核不通过
     * 101 - 待审核
     */
    public static List<Integer> EXCLUDE_STATUSES = Arrays.asList(PASS_STATUS,FAIL_STATUS,WAITTING_AUTID_STATUS);
}
