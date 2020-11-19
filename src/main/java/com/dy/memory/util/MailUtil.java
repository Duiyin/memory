package com.dy.memory.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailUtil {

	/**
	 * 检查邮箱格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 获取6位数的随机数-验证码
	 * 
	 * @return
	 */
	public static String getSixRandom() {
		Random random = new Random();
		String fourRandom = random.nextInt(10000) + "";
		int randLength = fourRandom.length();
		if (randLength < 6) {
			for (int i = 1; i <= 6 - randLength; i++)
				fourRandom = "0" + fourRandom;
		}
		return fourRandom;
	}
}
