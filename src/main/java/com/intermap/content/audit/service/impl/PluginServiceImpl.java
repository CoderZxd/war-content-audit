package com.intermap.content.audit.service.impl;

import com.intermap.content.audit.dao.IPluginDao;
import com.intermap.content.audit.entity.Plugin;
import com.intermap.content.audit.service.IPluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project goodview
 * @Package com.goodview.goodview.service.impl
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 13:49 2019/3/14.
 */

@Service
public class PluginServiceImpl implements IPluginService {

    @Autowired
    private IPluginDao pluginDao;

    @Override
    public List<Plugin> getAllPlugins(Plugin plugin) {
        return pluginDao.getPlugins(new Plugin());
    }
}
