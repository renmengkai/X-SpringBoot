package com.neuqsoft.rest.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuqsoft.rest.authentication.detail.CustomUserDetailsService;
import com.neuqsoft.rest.common.utils.Constant;
import com.neuqsoft.rest.common.utils.R;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 **/
@Slf4j
public class AuthenticationTokenFilter extends BasicAuthenticationFilter {

	private RedisTemplate redisTemplate;

	private CustomUserDetailsService customUserDetailsService;

	private ObjectMapper objectMapper = new ObjectMapper();

	public AuthenticationTokenFilter(AuthenticationManager authenticationManager, RedisTemplate template, CustomUserDetailsService customUserDetailsService) {
		super(authenticationManager);
		this.redisTemplate = template;
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = request.getHeader(Constant.TOKEN);
		if (StrUtil.isBlank(token) || StrUtil.equals(token, "null")) {
			token = request.getParameter(Constant.TOKEN);
		}

		if (StrUtil.isNotBlank(token) && !StrUtil.equals(token, "null")) {
			Object userId = redisTemplate.opsForValue().get(token);
			if (ObjectUtil.isNull(userId)) {
				writer(response, "无效token");
				return;
			}
			UserDetails userDetails = customUserDetailsService.loadUserByUserId((Long) userId);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}


	@SneakyThrows
	public void writer(HttpServletResponse response, String msg) {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(objectMapper.writeValueAsString(R.error(HttpServletResponse.SC_UNAUTHORIZED, msg)));
	}
}