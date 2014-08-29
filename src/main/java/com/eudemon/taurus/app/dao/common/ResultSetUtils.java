package com.eudemon.taurus.app.dao.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

public class ResultSetUtils {
	private static Logger logger = Logger.getLogger(ResultSetUtils.class);
	
	public static int getIntValue(ResultSet rs, String columnLabel, int defaultValue){
		int value = defaultValue;
		try {
			value = rs.getInt(columnLabel);
		} catch (SQLException e) {
			logger.error("get int value from result set fail", e);
		}
		
		return value;
	}
	
	public static String getStringValue(ResultSet rs, String columnLabel, String defaultValue){
		String value = defaultValue;
		try {
			value = rs.getString(columnLabel);
		} catch (SQLException e) {
			logger.error("get string value from result set fail", e);
		}
		
		return value;
	}
	
	public static Timestamp getTimestamp(ResultSet rs, String columnLabel, Timestamp defaultValue){
		Timestamp value = defaultValue;
		try {
			value = rs.getTimestamp(columnLabel);
		} catch (SQLException e) {
			logger.error("get time value from result set fail : " + e.getMessage());
		}
		
		return value;
	}
}
