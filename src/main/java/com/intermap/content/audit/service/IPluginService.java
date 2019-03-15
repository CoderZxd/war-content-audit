package com.intermap.content.audit.service;

import com.intermap.content.audit.entity.Plugin;

import java.util.List;

/**
 * @Project goodview
 * @Package com.goodview.goodview.service
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 13:46 2019/3/14.
 */
public interface IPluginService {
    List<Plugin> getAllPlugins(Plugin plugin);
}
