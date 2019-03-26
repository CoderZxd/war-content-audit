package com.intermap.content.audit.service.impl;

import com.intermap.content.audit.dao.DataRecordDao;
import com.intermap.content.audit.entity.DataRecord;
import com.intermap.content.audit.service.IContentAuditService;
import com.intermap.content.audit.utils.CommonUtil;
import com.intermap.content.audit.utils.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                        params.put("tableName", CommonUtil.getTableName());
                        params.put("sysStatus", ConstantUtil.SYS_STATUS);
                        params.put("limitNum",ConstantUtil.LIMITNUM);
                        params.put("excludeStatuses",ConstantUtil.EXCLUDE_STATUSES);
                        params.put("waitingAuditStatus",ConstantUtil.WAITTING_AUTID_STATUS);
                        recordsList = getDataRecordsListForDistribution(params);
                    }
                }
            }
            if(!recordsList.isEmpty()){
                DataRecord dataRecord = recordsList.remove(0);
                if(dataRecord != null && dataRecord.getDataId() != null){
                    logger.info("dataRecord==="+dataRecord.toString());
                    Map<String,Object> params = new HashMap<String,Object>(16);
                    params.put("tableName",CommonUtil.getTableName());
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

    @Override
    public Integer updateDataForAudit(Map<String, Object> params) {
        try {
            return dataRecordDao.updateDataForAudit(params);
        }catch (Exception e){
            logger.error("更新data record(data_id="+params.get("dataId")+")审核状态异常。异常信息为:"+e.getMessage());
        }
        return 0;
    }
}
