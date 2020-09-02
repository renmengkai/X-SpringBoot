package com.neuqsoft.rest.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuqsoft.rest.modules.sys.entity.SysRoleMenu;


/**
 * 角色与菜单对应关系
 *
 * @author neuqsoft
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

	void saveOrUpdate(Long roleId, List<Long> menuIdList);

	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);

}
