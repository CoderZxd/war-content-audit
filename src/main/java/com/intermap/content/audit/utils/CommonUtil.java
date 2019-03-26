package com.intermap.content.audit.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.utils
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 17:48 2019/3/25.
 */
public class CommonUtil {


    /**
     * @FileName CommonUtil.java
     * @ClassName CommonUtil
     * @MethodName getTableName
     * @Desc 获取data recode表名
     * @author zouxiaodong
     * @date 2019/3/25 17:50
     * @Params []
     * @return java.lang.String
     */
    public static String getTableName(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return new StringBuffer(ConstantUtil.TABLE_NAME_PREFIX).append(dateFormat.format(new Date())).toString();
    }
}
