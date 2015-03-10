package com.eudemon.taurus.app.json;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.eudemon.taurus.app.common.Log;

public class JasonUtils {
	public static void writeJasonP(HttpServletResponse response, Object rs, String callback) {
		try {
			String jsn = JSON.toJSONString(rs);
			String jp = jsn;
			if (null != callback && !callback.equals("")) {
				jp = callback + "(" + jsn + ")";
			}
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(jp);
		} catch (IOException e) {
			Log.getErrorLogger().error("write jasonp exception, rs : " + rs, e);
		}
	}
}
