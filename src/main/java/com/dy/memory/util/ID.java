package com.dy.memory.util;

import java.util.UUID;

/**
 * 工具类 设置随机数ID
 * 
 * @author Tinghan
 *
 */
public class ID {

	public static String UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 截取前十位数字
	 * 
	 * @return
	 */
	public static String Intercept() {
		return UUID().substring(0, 10);
	}

	/**
	 * 设置初始名
	 * 
	 * @param name
	 * @return
	 */
	public static String initName(String name) {
		return name.toString().substring(0, name.indexOf("@"));
	}
}
