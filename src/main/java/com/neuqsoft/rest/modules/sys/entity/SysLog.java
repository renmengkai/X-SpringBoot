package com.neuqsoft.rest.modules.sys.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 系统日志
 *
 * @author neuqsoft
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysLog extends Model<SysLog> {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	//用户名
	private String username;

	//用户操作
	private String operation;

	//请求方法
	private String method;

	//请求参数
	private String params;

	//执行时长(毫秒)
	private Long time;

	//IP地址
	private String ip;

	//创建时间
	private Date createDate;
}
