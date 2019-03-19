package com.intermap.content.audit.service.impl;

import com.intermap.content.audit.dao.DataRecordDao;
import com.intermap.content.audit.entity.DataRecord;
import com.intermap.content.audit.service.IContentAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * @return java.util.List<com.intermap.content.audit.entity.DataRecord>
     * @FileName IContentAuditService.java
     * @ClassName IContentAuditService
     * @MethodName getDataRecordsListForDistribution
     * @Desc 获取分发的Data record,并将status状态修改为人工待审核状态(status=101)
     * @author zouxiaodong
     * @date 2019/3/18 16:27
     * @Params []
     */
    @Override
    public List<DataRecord> getDataRecordsListForDistribution(Map<String,Object> params) {
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
}
