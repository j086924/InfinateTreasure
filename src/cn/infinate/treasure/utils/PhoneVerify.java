package cn.infinate.treasure.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneVerify {
	
	/**
	 * 手机号码的验证
	 */
	public static boolean isPhoneNO(String phonenum) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phonenum);
		return m.matches();
	}

}
