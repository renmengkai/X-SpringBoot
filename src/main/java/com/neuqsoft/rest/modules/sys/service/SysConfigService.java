package com.neuqsoft.rest.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuqsoft.rest.modules.sys.entity.SysConfig;

/**
 * 系统配置信息
 *
 * @author neuqsoft
 */
public interface SysConfigService extends IService<SysConfig> {

	/**
	 * 根据key，更新value
	 */
	void updateValueByKey(String key, String value);

	/**
	 * 根据key，获取配置的value值
	 *
	 * @param key key
	 */
	String getValue(String key);

	/**
	 * 根据key，获取value的Object对象
	 *
	 * @param key key
	 * @param clazz Object对象
	 */
	<T> T getConfigObject(String key, Class<T> clazz);

}
