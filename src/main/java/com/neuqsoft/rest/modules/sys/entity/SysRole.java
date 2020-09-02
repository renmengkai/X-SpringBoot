package com.neuqsoft.rest.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色
 *
 * @author neuqsoft
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@TableId(value = "role_id", type = IdType.AUTO)
	private Long roleId;

	/**
	 * 角色名称
	 */
	@NotBlank(message = "角色名称不能为空")
	private String roleName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建者ID
	 */
	private Long createUserId;

	@TableField(exist = false)
	private List<Long> menuIdList;

	/**
	 * 创建时间
	 */
	private Date createTime;

}
