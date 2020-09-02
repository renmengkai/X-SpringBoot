package com.neuqsoft.rest.modules.gen.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neuqsoft.rest.modules.gen.entity.ColumnEntity;
import com.neuqsoft.rest.modules.gen.entity.InfoRmationSchema;
import org.apache.ibatis.annotations.Param;

public interface SysGenMapper extends BaseMapper<InfoRmationSchema> {

	IPage<InfoRmationSchema> queryTableList(IPage page, @Param("ew") QueryWrapper<InfoRmationSchema> entityWrapper);

	InfoRmationSchema queryTableList(@Param("ew") QueryWrapper<InfoRmationSchema> entityWrapper);

	List<ColumnEntity> queryColumns(@Param("ew") QueryWrapper<ColumnEntity> entityWrapper);
}
