package com.eudemon.taurus.app.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.eudemon.taurus.app.common.Log;

public class StringUtils {
	public static String toString(String[] sa, String spliter){
		String ss = "";
		try {
			for(String s : sa){
				ss += s + ",";
			}
			ss = ss.substring(0, ss.length() - 1);
		} catch (Exception e) {
			Log.getDebugLogger().debug("StringUtils toString exception : " + e.getMessage());
		}
		
		return ss;
	}
	
	public static String toString(int[] ia, String spliter){
		String ss = "";
		try {
			for(int it : ia){
				ss += it + ",";
			}
			ss = ss.substring(0, ss.length() - 1);
		} catch (Exception e) {
			Log.getDebugLogger().debug("StringUtils toString exception : " + e.getMessage());
		}
		
		return ss;
	}
	
	public static int toInt(String value, int defaultValue){
		int n = defaultValue;
		if(null == value || value.equals("")){
			return n;
		}
		try {
			n = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Log.getDebugLogger().debug("StringUtils toInt exception : " + e.getMessage());
		}
		
		return n;
	}
	
	public static int[] toIntArray(String value, String spliter, int defaultValue){
		String[] strAy = value.split(spliter);
		int[] intAy = null;
		for(String str : strAy){
			intAy = ArrayUtils.add(intAy, toInt(str, defaultValue));
		}
		
		return intAy;
	}
	
	public static void main(String[] args){
		System.out.println(StringUtils.toString(new String[]{"1", "2"}, ","));
		
		System.out.println(ToStringBuilder.reflectionToString(StringUtils.toIntArray("1,2", ",", 0)));
	}
}
