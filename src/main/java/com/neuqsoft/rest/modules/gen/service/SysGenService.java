package com.neuqsoft.rest.modules.gen.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuqsoft.rest.modules.gen.entity.GenConfig;
import com.neuqsoft.rest.modules.gen.entity.InfoRmationSchema;

public interface SysGenService extends IService<InfoRmationSchema> {

	IPage<InfoRmationSchema> queryTableList(IPage page, QueryWrapper<InfoRmationSchema> entityWrapper);

	byte[] generatorCode(GenConfig config);
}
