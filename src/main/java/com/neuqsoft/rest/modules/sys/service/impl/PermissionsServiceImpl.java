package com.neuqsoft.rest.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.neuqsoft.rest.common.utils.Constant;
import com.neuqsoft.rest.modules.sys.entity.SysMenu;
import com.neuqsoft.rest.modules.sys.mapper.SysUserMapper;
import com.neuqsoft.rest.modules.sys.service.PermissionsService;
import com.neuqsoft.rest.modules.sys.service.SysMenuService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Lazy
@Service
@AllArgsConstructor
public class PermissionsServiceImpl implements PermissionsService {

	private final SysMenuService sysMenuService;

	private final SysUserMapper sysUserMapper;

	@Override
	public Set<String> getUserPermissions(long userId) {
		List<String> permsList;

		//系统管理员，拥有最高权限
		if (userId == Constant.SUPER_ADMIN) {
			List<SysMenu> menuList = sysMenuService.list();
			permsList = new ArrayList<>(menuList.size());
			for (SysMenu menu : menuList) {
				permsList.add(menu.getPerms());
			}
		}
		else {
			permsList = sysUserMapper.queryAllPerms(userId);
		}
		//用户权限列表
		Set<String> permsSet = new HashSet<>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		return permsSet;
	}
}
