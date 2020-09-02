package com.neuqsoft.rest.modules.sys.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuqsoft.rest.common.exception.RRException;
import com.neuqsoft.rest.common.utils.Constant;
import com.neuqsoft.rest.modules.sys.entity.SysRole;
import com.neuqsoft.rest.modules.sys.mapper.SysRoleMapper;
import com.neuqsoft.rest.modules.sys.service.SysRoleMenuService;
import com.neuqsoft.rest.modules.sys.service.SysRoleService;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 角色
 *
 * @author neuqsoft
 */

@Lazy
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	private final SysRoleMapper sysRoleMapper;

	private final SysRoleMenuService sysRoleMenuService;

	@Override
	@Transactional
	public void saveRoleMenu(SysRole role) {
		role.setCreateTime(new Date());
		sysRoleMapper.insert(role);
		checkPrems(role);
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void updateRoleMenu(SysRole role) {
		sysRoleMapper.updateById(role);
		checkPrems(role);
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return sysRoleMapper.queryRoleIdList(createUserId);
	}

	/**
	 * @return void
	 **/
	@Override
	public void deleteBath(Long[] ids) {
		baseMapper.deleteBatchIds(Arrays.asList(ids));
		//Arrays.stream(ids).forEach(id->baseMapper.deleteById(id));
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRole role) {
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if (role.getCreateUserId() == Constant.SUPER_ADMIN) {
			return;
		}
		List<Long> menuIdList = sysRoleMapper.queryAllMenuId(role.getCreateUserId());
		if (!menuIdList.containsAll(role.getMenuIdList())) {
			throw new RRException("新增角色的权限，已超出你的权限范围");
		}
	}
}
