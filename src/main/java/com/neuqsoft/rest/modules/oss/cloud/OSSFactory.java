package com.neuqsoft.rest.modules.oss.cloud;

import com.neuqsoft.rest.common.utils.ConfigConstant;
import com.neuqsoft.rest.common.utils.Constant;
import com.neuqsoft.rest.modules.sys.service.SysConfigService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

/**
 * 文件上传Factory
 *
 * @author neuqsoft
 */
@Component
@AllArgsConstructor
public class OSSFactory {
	private SysConfigService sysConfigService;

	public CloudStorageService build() {
		//获取云存储配置信息
		CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

		if (config.getType() == Constant.CloudService.QINIU.getValue()) {
			return new QiniuCloudStorageService(config);
		}
		else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
			return new AliyunCloudStorageService(config);
		}
		else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
			return new QcloudCloudStorageService(config);
		}
		else if (config.getType() == Constant.CloudService.MINIO.getValue()) {
			return new MinioCloudStorageService(config);
		}
		return null;
	}

}
