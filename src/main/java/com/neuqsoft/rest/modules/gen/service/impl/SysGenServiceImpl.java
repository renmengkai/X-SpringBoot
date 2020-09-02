package com.neuqsoft.rest.modules.gen.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuqsoft.rest.common.utils.GenUtils;
import com.neuqsoft.rest.modules.gen.entity.ColumnEntity;
import com.neuqsoft.rest.modules.gen.entity.GenConfig;
import com.neuqsoft.rest.modules.gen.entity.InfoRmationSchema;
import com.neuqsoft.rest.modules.gen.mapper.SysGenMapper;
import com.neuqsoft.rest.modules.gen.service.SysGenService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SysGenServiceImpl extends ServiceImpl<SysGenMapper, InfoRmationSchema> implements SysGenService {

	private final SysGenMapper sysGenMapper;

	private final GenUtils genUtils;

	@Override
	public IPage<InfoRmationSchema> queryTableList(IPage page, QueryWrapper<InfoRmationSchema> entityWrapper) {
		return sysGenMapper.queryTableList(page, entityWrapper);
	}

	public byte[] generatorCode(GenConfig config) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for (String tableName : config.getGenTable()) {
			//查询表信息
			InfoRmationSchema table = sysGenMapper.queryTableList(new QueryWrapper<InfoRmationSchema>().eq("tableName", tableName));
			//查询列信息
			List<ColumnEntity> columns = sysGenMapper.queryColumns(new QueryWrapper<ColumnEntity>().eq("tableName", tableName));
			//生成代码
			genUtils.generatorCode(config, table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
}
