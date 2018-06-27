package com.kitri.util;

public class ParameterCheck {
	public static String nullToBlank(String str) {
		return str== null ? "" : str;
	} //nullPoint에러
	
	public static int naNToOne(String numStr) {
		int num= 0;
		if(isNumber(numStr))
			num= Integer.parseInt(numStr);
		
		return num;
	}
	
	public static int naNToZero(String numStr) {
		int num= 0;
		if(isNumber(numStr))
			num= Integer.parseInt(numStr);
		
		return num;
	}

	private static boolean isNumber(String numStr) {
		if(numStr== null || numStr.isEmpty())
			return false;
		
		for (int i = 0; i < numStr.length(); i++) {
			if(!(numStr.charAt(i)>=48 && numStr.charAt(i)<=57)) //숫자
				return false;
			break;
		}
		
		return true;
	}
}
