package com.eudemon.taurus.app.util;

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
		try {
			n = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Log.getDebugLogger().debug("StringUtils toInt exception : " + e.getMessage());
		}
		
		return n;
	}
	
	public static void main(String[] args){
		System.out.println(StringUtils.toString(new String[]{"1", "2"}, ","));
	}
}
