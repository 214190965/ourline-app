package com.ourline.ourlinecommon.util;

import java.util.UUID;

/**
 * @ClassName UUIDGenerator
 * @Description UUID生成器
 * @date 20210303 09:44:14
 * @Copyright
 */
public class UUIDGenerator {

	/**
	 * @Title uuid
	 * @Description JDK默认UUID
	 * @return
	 */
	public static String uuid() {

		return UUID.randomUUID().toString();
	}

	/**
	 * @Title uuid32
	 * @Description 该方法用来产生一个32位的String唯一标记
	 * @return
	 */
	public static String uuid32() {

		String s = UUID.randomUUID().toString();

		return s.replace("-", "");
	}

}
