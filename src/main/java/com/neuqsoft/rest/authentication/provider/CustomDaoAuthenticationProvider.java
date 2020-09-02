package com.neuqsoft.rest.authentication.provider;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author neuqsoft
 */
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

	@Override
	public void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException("认证错误");
		}
		else {
			String presentedPassword = authentication.getCredentials().toString();
			if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
				throw new BadCredentialsException("帐号或密码错误");
			}
		}
	}
}
