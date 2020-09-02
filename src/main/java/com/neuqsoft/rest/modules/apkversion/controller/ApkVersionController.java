package com.neuqsoft.rest.modules.apkversion.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neuqsoft.rest.common.annotation.SysLog;
import com.neuqsoft.rest.common.base.AbstractController;
import com.neuqsoft.rest.common.utils.R;
import com.neuqsoft.rest.modules.apkversion.entity.ApkVersion;
import com.neuqsoft.rest.modules.apkversion.service.ApkVersionService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * APK版本管理
 *
 * @author neuqsoft
 */
@RestController
@AllArgsConstructor
@RequestMapping("/apkversion/apkversion")
public class ApkVersionController extends AbstractController {
	private final ApkVersionService apkVersionService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@PreAuthorize("hasRole('apkversion:apkversion:list')")
	public R list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		QueryWrapper<ApkVersion> queryWrapper = new QueryWrapper<>();
		IPage<ApkVersion> sysConfigList = apkVersionService.page(mpPageConvert.<ApkVersion>pageParamConvert(params), queryWrapper);
		return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@PreAuthorize("hasRole('apkversion:apkversion:info')")
	public R info(@PathVariable("id") Long id) {
		return R.ok().put("apkVersion", apkVersionService.getById(id));
	}


	/**
	 * 新增APK版本管理
	 */
	@SysLog("新增APK版本管理数据")
	@RequestMapping("/save")
	@PreAuthorize("hasRole('apkversion:apkversion:save')")
	public R save(@RequestBody ApkVersion apkVersion) {
		apkVersion.setUserId(getUserId());
		apkVersionService.save(apkVersion);
		return R.ok();
	}


	/**
	 * 修改
	 */
	@SysLog("修改APK版本管理数据")
	@RequestMapping("/update")
	@PreAuthorize("hasRole('apkversion:apkversion:update')")
	public R update(@RequestBody ApkVersion apkVersion) {
		apkVersion.setUpdateTime(new Date());
		apkVersionService.updateById(apkVersion);
		return R.ok();
	}


	/**
	 * 删除
	 */
	@SysLog("删除APK版本管理数据")
	@RequestMapping("/delete")
	@PreAuthorize("hasRole('apkversion:apkversion:delete')")
	public R delete(@RequestBody Long[] ids) {
		apkVersionService.removeByIds(Arrays.asList(ids));
		return R.ok();
	}

}
