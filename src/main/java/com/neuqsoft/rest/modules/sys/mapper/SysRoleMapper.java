package com.neuqsoft.rest.modules.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuqsoft.rest.modules.sys.entity.SysRole;

/**
 * 角色管理
 *
 * @author neuqsoft
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

}
