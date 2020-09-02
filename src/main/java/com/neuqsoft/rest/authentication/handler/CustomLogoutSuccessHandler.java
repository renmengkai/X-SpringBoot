package com.neuqsoft.rest.authentication.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuqsoft.rest.common.utils.Constant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/**
 * @author neuqsoft
 */
@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutHandler {

	@Autowired
	private RedisTemplate redisTemplate;

	@SneakyThrows
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String token = request.getHeader(Constant.TOKEN);
		redisTemplate.delete(Constant.AUTHENTICATION_TOKEN + token);
		redisTemplate.delete(token);
	}
}
