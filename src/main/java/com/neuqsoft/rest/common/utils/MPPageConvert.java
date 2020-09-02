package com.neuqsoft.rest.common.utils;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.stereotype.Component;

/**
 *
 **/
@Component
public class MPPageConvert {

	/**
	 * @return com.baomidou.mybatisplus.core.metadata.IPage<T>
	 **/
	public <T> IPage<T> pageParamConvert(Map<String, Object> param) {
		int currPage = 1;
		int limit = 10;
		if (MapUtil.getInt(param, "page") != null) {
			currPage = MapUtil.getInt(param, "page");
		}
		if (MapUtil.getInt(param, "limit") != null) {
			limit = MapUtil.getInt(param, "limit");
		}
		IPage<T> page = new Page<>(currPage, limit);
		return page;
	}

	/**
	 * @return java.util.HashMap
	 **/
	public HashMap pageValueConvert(IPage<?> page) {
		HashMap<Object, Object> pageData = new HashMap<>();
		pageData.put("list", page.getRecords());
		pageData.put("totalCount", page.getTotal());
		pageData.put("pageSize", page.getSize());
		pageData.put("currPage", page.getCurrent());
		pageData.put("totalPage", page.getPages());
		return pageData;
	}
}
