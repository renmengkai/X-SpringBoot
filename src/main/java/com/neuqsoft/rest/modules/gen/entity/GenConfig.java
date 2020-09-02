package com.neuqsoft.rest.modules.gen.entity;

import java.util.List;

import lombok.Data;

/**
 * Created by neuqsoft on 2019/4/28.
 */
@Data
public class GenConfig {
	private String mainPath;

	private String packagePath;

	private String moduleName;

	private String author;

	private String email;

	private List<String> genTable;
}
