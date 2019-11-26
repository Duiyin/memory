package com.dy.memory.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类 检查邮箱格式
 * @author Tinghan
 *
 */
public class ValidTool {

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
