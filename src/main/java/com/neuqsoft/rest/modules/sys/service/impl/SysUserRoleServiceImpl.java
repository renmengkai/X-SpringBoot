package com.neuqsoft.rest.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuqsoft.rest.modules.sys.entity.SysUserRole;
import com.neuqsoft.rest.modules.sys.mapper.SysUserRoleMapper;
import com.neuqsoft.rest.modules.sys.service.SysUserRoleService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


/**
 * 用户与角色对应关系
 *
 * @author neuqsoft
 */
@Service
@AllArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
