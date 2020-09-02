package com.neuqsoft.rest.modules.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuqsoft.rest.modules.sys.entity.SysUser;

/**
 * 系统用户
 *
 * @author neuqsoft
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 查询用户的所有权限
	 *
	 * @param userId 用户ID
	 */
	List<String> queryAllPerms(Long userId);
}
