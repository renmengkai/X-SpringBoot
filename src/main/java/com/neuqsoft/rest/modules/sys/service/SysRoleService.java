package com.neuqsoft.rest.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuqsoft.rest.modules.sys.entity.SysRole;


/**
 * 角色
 *
 * @author neuqsoft
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);

	void deleteBath(Long[] ids);

	void saveRoleMenu(SysRole role);

	void updateRoleMenu(SysRole role);

}
