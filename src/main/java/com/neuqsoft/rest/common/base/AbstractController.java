package com.neuqsoft.rest.common.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuqsoft.rest.authentication.detail.CustomUserDetailsUser;
import com.neuqsoft.rest.common.utils.MPPageConvert;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Controller公共组件
 *
 * @author neuqsoft
 */

public abstract class AbstractController {
	@Autowired
	protected MPPageConvert mpPageConvert;

	protected ObjectMapper objectMapper = new ObjectMapper();

	protected CustomUserDetailsUser getUser() {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (object != null) {
			return (CustomUserDetailsUser) object;
		}
		return null;
	}

	@SneakyThrows
	protected Long getUserId() {
		return getUser() == null ? null : getUser().getUserId();
	}
}
