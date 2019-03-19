package com.intermap.content.audit.controller;

import com.intermap.content.audit.entity.DataRecord;
import com.intermap.content.audit.service.IContentAuditService;
import com.intermap.content.audit.utils.ContentDistributionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project goodview
 * @Package com.intermap.content.audit.controller
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 11:23 2019/3/14.
 */
@RestController
@EnableAutoConfiguration
public class ContentAuditController {

    private static Logger logger = LoggerFactory.getLogger(ContentAuditController.class);

    @Autowired
    private IContentAuditService contentAuditService;

    @RequestMapping("/")
    public Object home() {
        logger.info("test!!!!!!!!!!!!!!!!!!!!!!!!!");
        DataRecord dataRecord = ContentDistributionUtil.getDataRecordFromDb();
        return dataRecord;
    }
}
