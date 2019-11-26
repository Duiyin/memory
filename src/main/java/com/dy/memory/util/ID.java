package com.dy.memory.util;

import java.util.UUID;

/**
 * 工具类 设置随机数ID
 * @author Tinghan
 *
 */
public class ID {

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 截取前十位数字
	 * @return
	 */
	public static String Intercept() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
	}
}
