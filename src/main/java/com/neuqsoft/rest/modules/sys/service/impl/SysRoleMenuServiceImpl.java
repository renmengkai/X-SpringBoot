package com.neuqsoft.rest.modules.sys.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuqsoft.rest.modules.sys.entity.SysRoleMenu;
import com.neuqsoft.rest.modules.sys.mapper.SysRoleMenuMapper;
import com.neuqsoft.rest.modules.sys.service.SysRoleMenuService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 角色与菜单对应关系
 *
 * @author neuqsoft
 */
@Service
@AllArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

	private final SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleId, roleId));
		if (CollUtil.isNotEmpty(menuIdList)) {
			menuIdList.forEach(id -> {
				SysRoleMenu menu = new SysRoleMenu();
				menu.setRoleId(roleId);
				menu.setMenuId(id);
				sysRoleMenuMapper.insert(menu);
			});
		}
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return sysRoleMenuMapper
				.selectList(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleId, roleId))
				.stream()
				.map(SysRoleMenu::getMenuId)
				.collect(Collectors.toList());
	}

}
