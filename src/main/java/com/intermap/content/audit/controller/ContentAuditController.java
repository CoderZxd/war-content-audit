package com.intermap.content.audit.controller;

import com.intermap.content.audit.entity.Plugin;
import com.intermap.content.audit.service.IPluginService;
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

    @Autowired
    private IPluginService pluginService;

    @RequestMapping("/")
    public Object home() {
        return pluginService.getAllPlugins(new Plugin());
    }
}
