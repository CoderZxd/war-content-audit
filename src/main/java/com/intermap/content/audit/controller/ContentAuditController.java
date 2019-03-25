package com.intermap.content.audit.controller;

import com.intermap.content.audit.entity.ResponseDto;
import com.intermap.content.audit.service.IContentAuditService;
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
@RequestMapping("/")
public class ContentAuditController {

    private static Logger logger = LoggerFactory.getLogger(ContentAuditController.class);

    @Autowired
    private IContentAuditService contentAuditService;

    @RequestMapping("/getDataForAudit")
    public ResponseDto getDataForAudit() {
        logger.info("用户拉取data record!");
        Object dataRecord = contentAuditService.getDataForAuditFromDb();
        return ResponseDto.success(dataRecord);
    }
}
