package com.neuqsoft.rest.modules.apkversion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuqsoft.rest.modules.apkversion.entity.ApkVersion;
import com.neuqsoft.rest.modules.apkversion.mapper.ApkVersionMapper;
import com.neuqsoft.rest.modules.apkversion.service.ApkVersionService;

import org.springframework.stereotype.Service;


@Service
public class ApkVersionServiceImpl extends ServiceImpl<ApkVersionMapper, ApkVersion> implements ApkVersionService {

}
