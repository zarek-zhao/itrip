package com.zarek.itrip.util;

import java.util.Random;

/**
 * <b>激活码工具类</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
public class ActivationCodeUtil {

	/**
	 * <b>产生一个六位的激活码</b>
	 * @return
	 */
	public static String createActivationCode() {
		StringBuffer sb = new StringBuffer();
		// 创建 Random 对象
		Random random = new Random();
		while (sb.length() < 6) {
			// 产生一个随机数
			sb.append(random.nextInt(10));
		}

		return sb.toString();
	}
}
