package com.neuqsoft.rest.modules.sys.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neuqsoft.rest.common.annotation.SysLog;
import com.neuqsoft.rest.common.base.AbstractController;
import com.neuqsoft.rest.common.utils.Constant;
import com.neuqsoft.rest.common.utils.R;
import com.neuqsoft.rest.common.validator.ValidatorUtils;
import com.neuqsoft.rest.modules.sys.entity.SysRole;
import com.neuqsoft.rest.modules.sys.service.SysRoleMenuService;
import com.neuqsoft.rest.modules.sys.service.SysRoleService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理
 *
 * @author neuqsoft
 */

@RestController
@RequestMapping("/sys/role")
@AllArgsConstructor
public class SysRoleController extends AbstractController {
	private final SysRoleService sysRoleService;

	private final SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 */
	@RequestMapping("/list")
	@PreAuthorize("hasRole('sys:role:list')")
	public R list(@RequestParam Map<String, Object> params) {
		QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
		//如果不是超级管理员，则只查询自己创建的角色列表
		if (getUserId() != Constant.SUPER_ADMIN) {
			queryWrapper.eq("create_user_id", getUserId());
		}

		//查询列表数据
		if (MapUtil.getStr(params, "key") != null) {
			queryWrapper
					.like("role_name", MapUtil.getStr(params, "key"));
		}
		IPage<SysRole> sysRoleIPage = sysRoleService.page(mpPageConvert.<SysRole>pageParamConvert(params), queryWrapper);

		return R.ok().put("page", mpPageConvert.pageValueConvert(sysRoleIPage));
	}

	/**
	 * 角色列表
	 */
	@RequestMapping("/select")
	@PreAuthorize("hasRole('sys:role:select')")
	public R select() {
		List<SysRole> list;
		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if (getUserId() != Constant.SUPER_ADMIN) {
			list = sysRoleService.list(Wrappers
					.<SysRole>query()
					.lambda()
					.eq(SysRole::getCreateUserId, getUserId())
			);
		}
		else {
			list = sysRoleService.list();
		}
		return R.ok().put("list", list);
	}

	/**
	 * 角色信息
	 */
	@RequestMapping("/info/{roleId}")
	@PreAuthorize("hasRole('sys:role:info')")
	public R info(@PathVariable("roleId") Long roleId) {
		SysRole role = sysRoleService.getById(roleId);

		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

		return R.ok().put("role", role);
	}

	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@RequestMapping("/save")
	@PreAuthorize("hasRole('sys:role:save')")
	public R save(@RequestBody SysRole role) {
		ValidatorUtils.validateEntity(role);

		role.setCreateUserId(getUserId());
		sysRoleService.saveRoleMenu(role);

		return R.ok();
	}

	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@RequestMapping("/update")
	@PreAuthorize("hasRole('sys:role:update')")
	public R update(@RequestBody SysRole role) {
		ValidatorUtils.validateEntity(role);

		role.setCreateUserId(getUserId());
		sysRoleService.updateRoleMenu(role);

		return R.ok();
	}

	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@RequestMapping("/delete")
	@PreAuthorize("hasRole('sys:role:delete')")
	public R delete(@RequestBody Long[] roleIds) {
		sysRoleService.deleteBath(roleIds);
		return R.ok();
	}
}
