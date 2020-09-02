package com.neuqsoft.rest.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuqsoft.rest.modules.sys.entity.SysLog;
import com.neuqsoft.rest.modules.sys.mapper.SysLogMapper;
import com.neuqsoft.rest.modules.sys.service.SysLogService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
