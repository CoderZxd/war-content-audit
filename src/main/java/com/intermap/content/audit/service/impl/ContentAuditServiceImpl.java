package com.intermap.content.audit.service.impl;

import com.intermap.content.audit.dao.DataRecordDao;
import com.intermap.content.audit.entity.DataRecord;
import com.intermap.content.audit.service.IContentAuditService;
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
        List<DataRecord> records = dataRecordDao.getDataRecordsListForDistribution(params);
        if(!records.isEmpty()){
            List<Long> ids = new ArrayList<Long>(10);
            for(DataRecord dataRecord:records){
                ids.add(dataRecord.getDataId());
            }
            params.put("ids",ids);
            int updateNums = dataRecordDao.updateDataRecordsToWaittingAuditStatus(params);
            System.out.println("records.size()="+records.size()+",update nums="+updateNums);
        }
        return records;
    }
}
