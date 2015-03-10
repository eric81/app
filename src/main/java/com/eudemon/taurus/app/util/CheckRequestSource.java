package com.eudemon.taurus.app.util;

import javax.servlet.http.HttpServletRequest;

import com.eudemon.taurus.app.common.Config;

/**
 * 检测请求是否来自手机
 *
 */
public class CheckRequestSource {
	
	static String[] mobilePhoneOs = {"Android","iPhone","iPad","iPod","Symbian","UA","BlackBerry","HTC","LG","Motorola","Nokia","Samsung","SonyEricsson"};
	
	public static boolean isFromPhone(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		
		//先检测是否命中要排除的选项，有命中则返回false
		String excludeString = Config.getString("exclude-mobile-devices", "");
		if(!"".equals(excludeString)){
			String[] excludeDevice = excludeString.split(";");
			if(excludeDevice.length>0){
				for(String device:excludeDevice){
					if(userAgent.contains(device)){
						return false;
					}
				}
			}
		}
		for(String os:mobilePhoneOs){
			if(userAgent.contains(os)){
				return true;
			}
		}
		return false;
	}

	public static boolean isFromPad(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("iPad")){
			return true;
		}
		return false;
	}
}
