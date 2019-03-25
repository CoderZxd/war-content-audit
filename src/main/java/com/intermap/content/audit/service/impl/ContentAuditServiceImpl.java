package com.intermap.content.audit.service.impl;

import com.intermap.content.audit.dao.DataRecordDao;
import com.intermap.content.audit.entity.DataRecord;
import com.intermap.content.audit.service.IContentAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.service.impl
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 14:34 2019/3/18.
 */
@Service
public class ContentAuditServiceImpl implements IContentAuditService {

    private static Logger logger = LoggerFactory.getLogger(ContentAuditServiceImpl.class);

    @Autowired
    private DataRecordDao dataRecordDao;

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
     * @return java.util.List<com.intermap.content.audit.entity.DataRecord>
     * @FileName IContentAuditService.java
     * @ClassName IContentAuditService
     * @MethodName getDataRecordsListForDistribution
     * @Desc 获取分发的Data record,并将status状态修改为人工待审核状态(status=101)
     * @author zouxiaodong
     * @date 2019/3/18 16:27
     * @Params []
     */
    private List<DataRecord> getDataRecordsListForDistribution(Map<String,Object> params) {
        List<DataRecord> records = new ArrayList<DataRecord>(10);
        try {
            records = dataRecordDao.getDataRecordsListForDistribution(params);
            if(!records.isEmpty()){
                List<Long> ids = new ArrayList<Long>(10);
                for(DataRecord dataRecord:records){
                    ids.add(dataRecord.getDataId());
                }
                params.put("ids",ids);
                int updateNums = dataRecordDao.updateDataRecordsToWaittingAuditStatus(params);
                logger.info("records.size()="+records.size()+",update nums="+updateNums);
                if(records.size() != updateNums){
                    logger.error("records.size()与updateNums不一致！！！！");
                }
            }
        }catch (Exception e){
            logger.error("获取分发的Data record,并将status状态修改为人工待审核状态(status=101)异常.异常信息为:"+e.getMessage());
        }
        return records;
    }


    /**
     * @FileName ContentAuditServiceImpl.java
     * @ClassName ContentAuditServiceImpl
     * @MethodName getDataForAuditFromDb
     * @Desc 从数据库获取data进行审核
     * @author zouxiaodong
     * @date 2019/3/21 14:08
     * @Params []
     * @return java.lang.Object
     */
    @Override
    public Object getDataForAuditFromDb() {
        try {
            if(recordsList.isEmpty()){
                synchronized (ContentAuditServiceImpl.class){
                    if(recordsList.isEmpty()){
                        Map<String,Object> params = new HashMap<String,Object>(16);
                        params.put("tableName",getTableName());
                        params.put("sysStatus",SYS_STATUS);
                        params.put("limitNum",LIMITNUM);
                        params.put("status",WAITTING_AUTID_STATUS);
                        recordsList = getDataRecordsListForDistribution(params);
                    }
                }
            }
            if(!recordsList.isEmpty()){
                DataRecord dataRecord = recordsList.remove(0);
                if(dataRecord != null && dataRecord.getDataId() != null){
                    logger.info("dataRecord==="+dataRecord.toString());
                    Map<String,Object> params = new HashMap<String,Object>(16);
                    params.put("tableName",getTableName());
                    params.put("dataId",dataRecord.getDataId());
                    Map<String,String> data = dataRecordDao.getDataInfoByDataRecord(params);
                    logger.info("data==="+data.toString());
                    return data;
                }
            }
        }catch (Exception e){
            logger.error("获取data records异常。异常信息为:"+e.getMessage());
        }
        return null;
    }

    /**
     * @FileName ContentAuditServiceImpl.java
     * @ClassName ContentAuditServiceImpl
     * @MethodName getTableName
     * @Desc 根据当前日期获取今天的data record表名
     * @author zouxiaodong
     * @date 2019/3/21 14:08
     * @Params []
     * @return java.lang.String
     */
    private String getTableName(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return new StringBuffer(TABLE_NAME_PREFIX).append(dateFormat.format(new Date())).toString();
    }
}
