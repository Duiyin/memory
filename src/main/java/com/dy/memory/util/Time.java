package com.dy.memory.util;

import java.sql.Timestamp;

/**
 * 工具类 设置当前时间
 * @author Tinghan
 *
 */
public class Time {
	public static Timestamp timestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
}

