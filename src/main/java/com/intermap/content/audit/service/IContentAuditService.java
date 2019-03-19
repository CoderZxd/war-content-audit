package com.intermap.content.audit.service;

import com.intermap.content.audit.entity.DataRecord;

import java.util.List;
import java.util.Map;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.service
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 14:29 2019/3/18.
 */
public interface IContentAuditService {

    /**
     * @FileName IContentAuditService.java
     * @ClassName IContentAuditService
     * @MethodName getDataRecordsListForDistribution
     * @Desc 获取分发的Data record
     * @author zouxiaodong
     * @date 2019/3/18 16:27
     * @Params [params]
     * @return java.util.List<com.intermap.content.audit.entity.DataRecord>
     */
    List<DataRecord> getDataRecordsListForDistribution(Map<String,Object> params);
}
