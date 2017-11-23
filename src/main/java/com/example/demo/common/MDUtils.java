package com.example.demo.common;

import java.security.MessageDigest;

/**
 * @Description: MD 不可逆加密
 * @author QuiFar
 * @date 2017年11月11日 下午10:28:35
 * @version V1.0
 */
public class MDUtils {
	/***
	 * 默认字符集 {@link CharEncoding#UTF_8}
	 */
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";

	/**
	 * MD5加密
	 * 
	 * @param data
	 *            待加密数据
	 * @return byte[] 消息摘要
	 * @throws Exception
	 */
	public static byte[] encodeMD5(byte[] data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		return md.digest(data);
	}

	/**
	 * MD5加密后再进行HEX 运算
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encodeMD5(String str) throws Exception {
		byte[] md5Str = encodeMD5(str.getBytes(DEFAULT_CHARSET_NAME));
		return HexUtils.encodeHexString(md5Str);
	}

	// public static void main(String[] args) throws Exception {
	// //System.out.println(MDUtils.encodeMD5Hex("123456"));
	//
	// }

}
