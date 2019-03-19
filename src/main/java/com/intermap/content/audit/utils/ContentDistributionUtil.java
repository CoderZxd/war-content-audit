package com.intermap.content.audit.utils;

import com.intermap.content.audit.entity.DataRecord;
import com.intermap.content.audit.service.IContentAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.utils
 * @Author：zouxiaodong
 * @Description:批量获取数据库内容分发给相关请求
 * @Date:Created in 16:05 2019/3/18.
 */
public class ContentDistributionUtil {

    private static Logger logger = LoggerFactory.getLogger(ContentDistributionUtil.class);

    private static IContentAuditService contentAuditService = SpringBeanUtil.getBean(IContentAuditService.class);

    /**
     * 代表机器审核不确定的状态
     */
    private static Integer SYS_STATUS = 1;

    /**
     * 等待被人工审核状态
     */
    private static Integer WAITTING_AUTID_STATUS = 101;

    /**
     * 每次获取的num数
     */
    private static Integer LIMITNUM = 20;

    /**
     * data_record表名前缀
     */
    private static String TABLE_NAME_PREFIX = "data_record_";


    /**
     * 存储缓存的db数据
     */
    private static List<DataRecord> recordsList = Collections.synchronizedList(new ArrayList<DataRecord>(20));

    /**
     * @FileName ContentDistributionUtil.java
     * @ClassName ContentDistributionUtil
     * @MethodName getDataRecordFromDb
     * @Desc 获取data record进行审核,支持多线程竞争获取
     * @author zouxiaodong
     * @date 2019/3/18 16:17
     * @Params []
     * @return com.intermap.content.audit.entity.DataRecord
     */
    public static DataRecord getDataRecordFromDb(){
        try {
            if(recordsList.isEmpty()){
                synchronized (ContentDistributionUtil.class){
                    if(recordsList.isEmpty()){
                        Map<String,Object> params = new HashMap<String,Object>(16);
                        params.put("tableName",getTableName());
                        params.put("sysStatus",SYS_STATUS);
                        params.put("limitNum",LIMITNUM);
                        params.put("status",WAITTING_AUTID_STATUS);
                        recordsList = contentAuditService.getDataRecordsListForDistribution(params);
                    }
                }
            }
            if(!recordsList.isEmpty()){
                DataRecord dataRecord = recordsList.remove(0);
                if(dataRecord != null && dataRecord.getDataId() != null){
                    logger.debug("dataRecord==="+dataRecord.toString());
                    return dataRecord;
                }
            }
        }catch (Exception e){
            logger.error("获取data records异常。异常信息为:"+e.getMessage());
        }
        return null;
    }

    /**
     * @FileName ContentDistributionUtil.java
     * @ClassName ContentDistributionUtil
     * @MethodName getTableName
     * @Desc 根据今天的时间生成data record表名
     * @author zouxiaodong
     * @date 2019/3/18 16:54
     * @Params []
     * @return java.lang.String
     */
    private static String getTableName(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return new StringBuffer(TABLE_NAME_PREFIX).append(dateFormat.format(new Date())).toString();
    }
}
