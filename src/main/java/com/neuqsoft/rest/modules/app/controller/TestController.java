package com.neuqsoft.rest.modules.app.controller;

import com.neuqsoft.rest.common.annotation.AuthIgnore;
import com.neuqsoft.rest.common.annotation.LoginUser;
import com.neuqsoft.rest.common.base.AbstractController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class TestController extends AbstractController {

	/**
	 * @return java.lang.String
	 **/
	@RequestMapping(value = "/getUserId")
	public String getUserId(@LoginUser String userId) {
		return "userId:" + userId;
	}

	/**
	 * @return java.lang.String
	 **/
	@AuthIgnore
	@RequestMapping(value = "/hello")
	public String hello() {
		return "--------------------hello";
	}

}
