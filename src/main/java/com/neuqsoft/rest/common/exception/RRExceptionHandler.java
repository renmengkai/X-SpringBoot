package com.neuqsoft.rest.common.exception;

import java.nio.file.AccessDeniedException;

import com.neuqsoft.rest.common.utils.R;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author neuqsoft
 */
@Slf4j
@RestControllerAdvice
public class RRExceptionHandler extends R {
	/**
	 * 自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e) {
		return R.error(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e) {
		return R.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AccessDeniedException.class)
	public R handleAccessDeniedException(AccessDeniedException e) {
		return R.error(HttpStatus.FORBIDDEN.value(), "没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e) {
		log.error("发生异常", e);
		return R.error(e.getMessage());
	}
}
