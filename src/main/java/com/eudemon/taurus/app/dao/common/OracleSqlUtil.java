package com.eudemon.taurus.app.dao.common;

import java.util.Calendar;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.eudemon.taurus.app.common.Config;

public class OracleSqlUtil {
	/**
	 * paged query
	 * 
	 * @param querySql
	 *            basic query sql
	 * @param page
	 *            page num
	 * @param pageSize
	 *            size of one page
	 * @return
	 */
	public static String getPagedSql(String querySql, int page, int pageSize) {
		if (null == querySql) {
			return "";
		}

		StringBuffer sqlBf = new StringBuffer(querySql);

		int n = pageSize * (page - 1) + 1;
		int m = pageSize * page;

		sqlBf
				.insert(0,
						"select d.* from (select row_.*, rownum rownum_ from (");
		sqlBf.insert(sqlBf.length(), ") row_ ) d where rownum_ <= " + m
				+ " and rownum_ >= " + n);

		return sqlBf.toString();
	}

	/**
	 * time between sql
	 * 
	 * @param timeField
	 *            field of time
	 * @param startTime
	 *            time of start
	 * @param endTime
	 *            time of end
	 * @return
	 */
	public static String getTimeBetweenSql(String startTime, String endTime) {
		if (null == startTime || startTime.equals("") || null == endTime
				|| endTime.equals("")) {
			return "";
		}
		return " between to_date('" + startTime
				+ "', 'yyyy-MM-dd hh24:mi:ss') and to_date('" + endTime
				+ "', 'yyyy-MM-dd hh24:mi:ss')";
	}

	public static String getTodayBetweenSql() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		String startTime = DateFormatUtils.format(calendar, Config.getString("app.timePatten"));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) + 1);
		String endTime = DateFormatUtils.format(calendar, Config.getString("app.timePatten"));

		return " between to_date('" + startTime
				+ "', 'yyyy-MM-dd hh24:mi:ss') and to_date('" + endTime
				+ "', 'yyyy-MM-dd hh24:mi:ss')";
	}

	public static void main(String[] args) {
		System.out.println(getPagedSql("select * from t_terminal", 1, 20));
		System.out.println(getTimeBetweenSql("2009-10-28 16:40:45",
				"2009-11-28 16:40:45"));
		System.out.println(getTodayBetweenSql());
	}
}
