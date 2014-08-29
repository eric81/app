package com.eudemon.taurus.app.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.eudemon.taurus.app.json.JsonMapper;

public class IPAddress {
	
	public static String getArea(String ip){
		HttpClient.setProxy("202.84.17.41", "8080");
		String region = "";
		
		String rs = "";
		try {
			rs = new String(HttpClient.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip, "", "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JsonMapper jmp =JsonMapper.nonDefaultMapper();
		Map<String, Object> map = jmp.fromJson(rs, HashMap.class);
		
		if(null != map && map.get("code") != null && (Integer)map.get("code") == 0){
			Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
			region = (String) dataMap.get("region");
		}
		
		return region;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(IPAddress.getArea("202.84.17.41"));
	}

}
