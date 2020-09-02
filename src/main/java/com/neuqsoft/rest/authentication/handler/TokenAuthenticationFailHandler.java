package com.neuqsoft.rest.authentication.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuqsoft.rest.common.utils.R;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 *
 **/
public class TokenAuthenticationFailHandler implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		PrintWriter writer = response.getWriter();
		writer.write(objectMapper.writeValueAsString(R.error(HttpStatus.UNAUTHORIZED.value(), authException.getMessage())));
	}
}
