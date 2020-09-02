package com.neuqsoft.rest.modules.sys.entity;


import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统配置信息
 *
 * @author neuqsoft
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysConfig extends Model<SysConfig> {

	@TableId
	private Long id;

	@NotBlank(message = "参数名不能为空")
	private String configKey;

	@NotBlank(message = "参数值不能为空")
	private String configValue;

	private Integer configStatus;

	private String remark;

}
