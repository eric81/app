package com.eudemon.taurus.app.http;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
		
		JSONObject jsObj = JSON.parseObject(rs);
		if(null != jsObj){
			JSONObject dataJs = jsObj.getJSONObject("data");
			if(null != dataJs){
				region = dataJs.getString("region");
			}
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
