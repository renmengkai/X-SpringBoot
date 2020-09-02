package com.neuqsoft.rest.authentication.detail;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomUserDetailsUser extends User implements Serializable {

	private Long userId;

	public CustomUserDetailsUser(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
	}
}
