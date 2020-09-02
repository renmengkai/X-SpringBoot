package com.neuqsoft.rest.modules.gen.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class InfoRmationSchema extends Model<InfoRmationSchema> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tableName;

	private String engine;

	private String tableComment;

	private String createTime;
}
