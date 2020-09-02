package com.neuqsoft.rest.modules.sys.vo;

import java.util.List;

import lombok.Data;

/**
 * @author neuqsoft
 */
@Data
public class RouterEntity {

	private Long menuId;

	private String path;

	private String component;

	private String redirect;

	private String name;

	private List<RouterEntity> children;
}
