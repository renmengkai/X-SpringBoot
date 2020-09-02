package com.neuqsoft.rest.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuqsoft.rest.modules.sys.entity.SysMenu;
import com.neuqsoft.rest.modules.sys.vo.RouterEntity;


/**
 * 菜单管理
 *
 * @author neuqsoft
 */
public interface SysMenuService extends IService<SysMenu> {

	/**
	 * 根据父菜单，查询子菜单
	 *
	 * @param parentId 父菜单ID
	 * @param menuIdList 用户菜单ID
	 */
	List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 *
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListParentId(Long parentId);

	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();

	/**
	 * 获取用户菜单列表
	 */
	List<SysMenu> getUserMenuList(Long userId);

	/**
	 * 查询用户的权限列表
	 */
	List<SysMenu> queryUserList(Long userId);


	List<RouterEntity> getUserMenu(Long userId);
}
