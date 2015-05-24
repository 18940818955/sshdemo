package com.exp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @project myoa
 * @version 0.1
 * @author sam zhang
 * @created at 2006-7-4 20:57:58
 * @purpose: provide common mehtod for string operations;
 * @edited by sam zhang
 */
public class StringUtil {

	/**
	 * ��յ if str is null then convret str to "".
	 * 
	 * @param str
	 * @return
	 */
	public static String convertStringIfNull(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 判断是否是有效的字符串，空串为无效串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidStr(String str) {
		return str != null && str.trim().length() > 0 && !("null".equals(str));
	}

	/**
	 * 判断是否是有效的字符串，空串为无效串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotValidStr(String str) {
		return !(isValidStr(str));
	}

	/**
	 * 根据字符串转换为布尔值�.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean getBooleanValue(String str) {
		return isValidStr(str) ? str.toLowerCase().trim().equals("true")
				: false;
	}

	/**
	 * convert str value to int. if fail,then return defaultvalue.
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static int getIntValue(String str, int defaultValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static void main(String arg[]) {
		System.out.println(isIntValue("023"));
	}

	/**
	 * convert str value to int. if fail,then return defaultvalue.
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static boolean isNumberType(String str) {

		boolean isInt = false;
		try {
			Double.parseDouble(str);
			isInt = true;
		} catch (Exception e) {

		}

		return isInt;
	}

	/**
	 * convert str value to int. if fail,then return defaultvalue.
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static boolean isIntValue(String str) {

		boolean isInt = false;
		try {
			Integer.parseInt(str);
			isInt = true;
		} catch (Exception e) {

		}

		return isInt;
	}

	/**
	 * convert String object to BigDecimal
	 * 
	 * @param param
	 * @return
	 */
	public static BigDecimal getBigDecimal(String param) {
		if (StringUtil.isValidStr(param)) {
			try {
				return new BigDecimal(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		return new BigDecimal(-1);
	}

	public static BigDecimal getBigDecimalZero(String param) {
		if (StringUtil.isValidStr(param)) {
			try {
				BigDecimal bd = new BigDecimal(param);
				if (bd.compareTo(BigDecimal.ZERO) == 0) {
					bd = BigDecimal.ZERO;
				}
				return bd;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		return BigDecimal.ZERO;
	}

	/**
	 * convert Object array to String.
	 * 
	 * @param values
	 * @return
	 */
	public static String describe(Object[] values) {
		StringBuffer buff = new StringBuffer();

		for (int m = 0; m < values.length; m++) {
			buff.append(values[m]).append(", ");
		}

		return buff.toString();
	}

	/**
	 * convert String to long.
	 * 
	 * @param value
	 * @return
	 */
	public static long getStrTolong(String value) {
		long result = 0;
		if (value == null || (value != null && value.equals(""))) {
			return result;
		}

		try {
			result = Long.parseLong(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * convert String to Integer.
	 * 
	 * @param value
	 * @return
	 */
	public static Integer getStrToInteger(String value) {
		Integer result = new Integer(0);
		value = value.trim();
		if (value == null || (value != null && value.equals(""))) {
			return result;
		}

		try {
			result = Integer.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * convert String to double
	 * 
	 * @param value
	 * @return
	 */
	public static double getStrTodouble(String value) {
		double result = 0;
		if (!isValidStr(value)) {
			return result;
		}
		try {
			result = Double.parseDouble(value);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List arrayToList(String a[]) {
		List l = new ArrayList();
		for (String s : a) {
			l.add(s);
		}
		return l;

	}

	public static String gengrateTwoBitString(String a) {
		if (isValidStr(a)) {
			if (a.length() == 1) {
				return "0" + a;
			} else {
				return a;
			}
		} else {
			return "";
		}

	}

	public static String getYear(String a) {

		String retStr = "";
		// 2012年1~12月 2012年10~12月 2012年1月
		if (StringUtil.isValidStr(a) && a.indexOf("年") > 0) {
			retStr = a.split("年")[0];
		}
		return retStr;

	}

	public static String getMonth(String a) {

		String retStr = "";
		// 2012年1~12月 2012年10~12月 2012年1月
		if (StringUtil.isValidStr(a) && a.indexOf("年") > 0) {

			String b = a.split("年")[1];
			String c = "";
			if (b.indexOf("~") > 0) {
				c = b.split("~")[0];
			} else {
				c = b.split("月")[0];
			}

			if (StringUtil.isValidStr(c)) {
				if (c.length() == 1) {
					retStr = "0" + c;
				} else {
					retStr = c;
				}
			}
		}
		return retStr;

	}

	public static String getTongJiQiLetter(String a) {

		String retStr = a;
		// 2012年1~12月 2012年10~12月 2012年1月
		if (StringUtil.isValidStr(a) && a.indexOf("年") > 0) {
			retStr = a.split("年")[0];
			String b = a.split("年")[1];
			String c = "";
			if (b.indexOf("~") > 0) {
				c = b.split("~")[0];
			} else {
				c = b.split("月")[0];
			}

			if (StringUtil.isValidStr(c)) {
				if (c.length() == 1) {
					retStr += "0" + c;
				} else {
					retStr += c;
				}
			}
		}
		return retStr;

	}

	public static String getStatisticsType(String a) {

		String retStr = a;
		if ("C".equals(a)) {
			retStr = "当期值";
		} else if ("A".equals(a)) {
			retStr = "累计值";
		}
		return retStr;

	}

	public static int getMontnSeason(String ms) {

		if (ms.startsWith("0")) {
			ms = ms.substring(1);
		}

		if (ms.equals("")) {
			ms = "0";
		}

		return Integer.parseInt(ms);

	}

	public static String getValueFromMap(Object Obj) {
		String retStr = "";
		if (Obj == null)
			retStr = "0";
		String o = String.valueOf(Obj);
		if (isValidStr(o)) {
			retStr = o;
		} else {
			retStr = "0";
		}

		return retStr;
	}

	public static String covertNullToSpaceStr(Object obj) {
		String retStr = "";
		if (obj != null) {
			retStr = String.valueOf(obj);
		}

		return retStr;
	}

	public static String doubleToStr(double doubleValue) {

		java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");

		return df.format(doubleValue);
	}

	public static String getObjectToStr(Object obj) {

		java.text.DecimalFormat df = new java.text.DecimalFormat();

		return df.format(obj).replaceAll(",", "");
	}

	public static double getObjhectToDouble(Object obj) {
		String st1 = String.valueOf(obj);

		double d1;
		d1 = StringUtil.isValidStr(st1) ? Double.parseDouble(st1) : 0.0;
		d1 = CalculateUtil.round(d1, 4);
		return d1;
	}

	public static boolean isEmpty(Object obj) {

		if (obj == null) {
			return true;
		} else if ("".equals(obj)) {
			return true;
		}
		return false;
	}
}
