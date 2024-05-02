package com.util;
import java.security.MessageDigest;
import java.util.Random;


public final class MD5 {

	public final static String getMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");

			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getMD5BySalt(String str, String salt) {
		return getMD5(getMD5(str) + salt);
	}

	public static String getSalt() {
		String salt = "";
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int num = rand.nextInt(3);
			switch (num) {
			case 0:
				char c1 = (char) (rand.nextInt(26) + 'a');
				salt += c1;
				break;
			case 1:
				char c2 = (char) (rand.nextInt(26) + 'A');
				salt += c2;
				break;
			case 2:
				salt += rand.nextInt(10);//生成随机数字
			}
		}
		return salt;
	}

}












































































