package com.eudemon.taurus.app.util;

import javax.servlet.http.HttpServletRequest;

import com.eudemon.taurus.app.common.Config;

/**
 * ��������Ƿ������ֻ�
 *
 */
public class CheckRequestSource {
	
	static String[] mobilePhoneOs = {"Android","iPhone","iPad","iPod","Symbian","UA","BlackBerry","HTC","LG","Motorola","Nokia","Samsung","SonyEricsson"};
	
	public static boolean isFromPhone(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		
		//�ȼ���Ƿ�����Ҫ�ų���ѡ��������򷵻�false
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
