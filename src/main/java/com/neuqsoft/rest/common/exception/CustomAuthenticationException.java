package com.neuqsoft.rest.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author neuqsoft
 */
public class CustomAuthenticationException extends AuthenticationException {

	public CustomAuthenticationException(String msg) {
		super(msg);
	}
}
