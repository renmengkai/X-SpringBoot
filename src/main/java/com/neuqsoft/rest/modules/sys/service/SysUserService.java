package com.neuqsoft.rest.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuqsoft.rest.modules.sys.entity.SysUser;


/**
 * 系统用户
 *
 * @author neuqsoft
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * 查询用户的所有权限
	 *
	 * @param userId 用户ID
	 */
	List<String> queryAllPerms(Long userId);

	/**
	 * 修改密码
	 *
	 * @param userId 用户ID
	 * @param password 原密码
	 * @param newPassword 新密码
	 */
	int updatePassword(Long userId, String password, String newPassword);

	void saveUserRole(SysUser user);

	void updateUserRole(SysUser user);

}
