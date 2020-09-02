package com.neuqsoft.rest.modules.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuqsoft.rest.modules.sys.entity.SysMenu;

/**
 * 菜单管理
 *
 * @author neuqsoft
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();

	/**
	 * 查询用户的权限列表
	 */
	List<SysMenu> queryUserList(Long userId);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
}
