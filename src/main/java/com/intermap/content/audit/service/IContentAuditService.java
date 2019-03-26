package com.intermap.content.audit.service;

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
     * @MethodName getDataForAuditFromDb
     * @Desc 从数据库获取data进行审核
     * @author zouxiaodong
     * @date 2019/3/21 14:07
     * @Params []
     * @return java.lang.Object
     */
    Object getDataForAuditFromDb();

    Integer updateDataForAudit(Map<String,Object> params);
}
