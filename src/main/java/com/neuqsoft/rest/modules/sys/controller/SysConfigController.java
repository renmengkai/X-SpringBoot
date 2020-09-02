package com.neuqsoft.rest.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neuqsoft.rest.common.annotation.SysLog;
import com.neuqsoft.rest.common.base.AbstractController;
import com.neuqsoft.rest.common.utils.R;
import com.neuqsoft.rest.common.validator.ValidatorUtils;
import com.neuqsoft.rest.modules.sys.entity.SysConfig;
import com.neuqsoft.rest.modules.sys.service.SysConfigService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统参数信息
 *
 * @author neuqsoft
 */
@RestController
@RequestMapping("/sys/config")
@AllArgsConstructor
public class SysConfigController extends AbstractController {
	private final SysConfigService sysConfigService;

	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@PreAuthorize("hasRole('sys:config:list')")
	public R list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		QueryWrapper<SysConfig> queryWrapper = new QueryWrapper<>();
		if (MapUtil.getStr(params, "key") != null) {
			queryWrapper
					.like("remark", MapUtil.getStr(params, "key"));
		}
		IPage<SysConfig> sysConfigList = sysConfigService.page(mpPageConvert.<SysConfig>pageParamConvert(params), queryWrapper);

		return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
	}


	/**
	 * 配置信息
	 */
	@RequestMapping("/info/{id}")
	@PreAuthorize("hasRole('sys:config:info')")
	public R info(@PathVariable("id") Long id) {
		SysConfig config = sysConfigService.getById(id);

		return R.ok().put("config", config);
	}

	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@RequestMapping("/save")
	@PreAuthorize("hasRole('sys:config:save')")
	public R save(@RequestBody SysConfig config) {
		ValidatorUtils.validateEntity(config);

		sysConfigService.save(config);

		return R.ok();
	}

	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@RequestMapping("/update")
	@PreAuthorize("hasRole('sys:config:update')")
	public R update(@RequestBody SysConfig config) {
		ValidatorUtils.validateEntity(config);
		sysConfigService.updateById(config);
		return R.ok();
	}

	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@RequestMapping("/delete")
	@PreAuthorize("hasRole('sys:config:delete')")
	public R delete(@RequestBody Long[] ids) {
		sysConfigService.removeByIds(Arrays.asList(ids));
		return R.ok();
	}

}
