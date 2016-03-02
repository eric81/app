package com.eudemon.taurus.app.http.use;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.eudemon.taurus.app.AppStartServer;
import com.eudemon.taurus.app.common.Log;
import com.eudemon.taurus.app.http.HttpClient;

public class Hospital {

	public static void loopHospitalNumber(int startNum, int endNum, String cookie, String encoding) {
		String baseUrl = "http://www.bjguahao.gov.cn/comm/guahao.php?hpid=122&ksid=1108&jiuz=&ybkh=&hzname=&hzsfz=&datid=";
		try {
			for (int i = startNum; i < endNum; i++) {
				String queryUrl = baseUrl + i;
				String queryResult = new String(HttpClient.get(queryUrl, cookie, encoding), encoding);

				int start = queryResult.indexOf("guahaol") + 9;
				int end = queryResult.indexOf("tpost") - 27;
				if (start > 0 && end > 0) {
					String info = queryResult.substring(start, end).trim();
					String dateInfo = info.substring(info.indexOf("就诊日期:") + 5, info.length() - 5);
					String doctorInfo = info.substring(info.indexOf("选择的医生:") + 6, info.indexOf("挂号费:") - 7);
					String departmentInfo = info.substring(info.indexOf("选择的科室:") + 6, info.indexOf("选择的医生:") - 7);
					Log.getDebugLogger().info("***datid=" + i + "***" + dateInfo + "***" + doctorInfo + "***" + departmentInfo);
				}else{
					Log.getDebugLogger().info(queryResult);
				}

				try {
					Thread.sleep(10 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void hospitalRegister(String dataid, String cookie, String encoding) {
		String baseUrl = "http://www.bjguahao.gov.cn";
		String queryUrl = baseUrl + "/comm/guahao.php?hpid=122&ksid=1108&jiuz=&ybkh=&hzname=&hzsfz=&dataid=" + dataid;
		String getSmsVericodeUrl = baseUrl
				+ "/comm/shortmsg/dx_code.php?hpid=122&ksid=1108&jiuz=&ybkh=&baoxiao=8&dataid=" + dataid;
		String registerUrl = baseUrl + "/comm/ghdown.php";

		try {
			String queryResult = new String(HttpClient.get(queryUrl, cookie, encoding), encoding);
			if (queryResult.indexOf("主任医师") > 0) {
				System.out.println("还有专家号，赶紧发短信");
				System.out.println(new String(HttpClient.get(getSmsVericodeUrl, cookie, encoding), encoding));
			} else {
				System.out.println("很遗憾已经没有专家号了");
				return;
			}

			System.out.println("please input verfy code:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String dxcode = "";
			dxcode = reader.readLine().trim();

			String req = "hpid=122&ksid=1108&jiuz=&ybkh=&baoxiao=8&tpost=d3c813b876eef842a6c7245ea831a04d&datid="
					+ dataid + "&dxcode=" + dxcode;
			System.out.println("提交挂号申请:" + registerUrl);
			System.out.println("挂号结果：" + new String(HttpClient.post(registerUrl, req, cookie, encoding), encoding));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] agrs) {
		AppStartServer.getInstance().startSystem(null);

		String encoding = "gb2312";
		// HttpClient.setProxy("192.168.2.59", "8080");
		HttpClient.setProxy("202.84.17.41", "8080");
		
		String cookie = "PHPSESSID=9edceb5d5d5048bfc31145f08847908e"
		+ ";__c_sesslist_45359=dfmg3n7gkw_c6n%252Cdfmbpxi7rc_c6n"
		+ ";Hm_lvt_13e29334f151c8514bf6cf2533b9d9af=1364345628,1364355165"
		+ ";Hm_lpvt_13e29334f151c8514bf6cf2533b9d9af=1364370326" + ";__c_pv_45359=43"
		+ ";__c_session_45359=1364355145806512" + ";__c_today_45359=2" + ";__c_review_45359=1"
		+ ";__c_last_45359=1364370307297" + ";__c_visitor=1357619616816718"
		+ ";__c_session_at_45359=1364370326072";
		
		//97965 98500
		loopHospitalNumber(98471, 98600, cookie, encoding);

		// hospitalRegister("98471", encoding);
	}
}
