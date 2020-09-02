package com.neuqsoft.rest.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.neuqsoft.rest.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author neuqsoft
 */
public abstract class Assert {

	public static void isBlank(String str, String message) {
		if (StringUtils.isBlank(str)) {
			throw new RRException(message);
		}
	}

	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new RRException(message);
		}
	}

	public static boolean checkCellphone(String cellphone) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
		return check(cellphone, regex);
	}

	static boolean flag = false;

	public static boolean check(String str, String regex) {
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			flag = matcher.matches();
		}
		catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
