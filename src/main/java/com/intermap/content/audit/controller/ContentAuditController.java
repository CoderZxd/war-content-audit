package com.intermap.content.audit.controller;

import com.intermap.content.audit.entity.ResponseDto;
import com.intermap.content.audit.enumeration.AuditCode;
import com.intermap.content.audit.service.IContentAuditService;
import com.intermap.content.audit.utils.CommonUtil;
import com.intermap.content.audit.utils.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @RequestMapping("/updateDataForAudit")
    public ResponseDto updateDataForAudit(@RequestBody Map<String,Object> params) {
        logger.info("更新data record!params={}",params);
        if(AuditCode.Y.getCode().equals(params.get("code")) || AuditCode.P.getCode().equals(params.get("code"))){
            params.put("status", ConstantUtil.PASS_STATUS);
        }else{
            params.put("status",ConstantUtil.FAIL_STATUS);
        }
        params.put("tableName", CommonUtil.getTableName());
        int num = contentAuditService.updateDataForAudit(params);
        logger.info("更新的记录条数为:{}",num);
        return ResponseDto.success(num);
    }
}
