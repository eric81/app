package com.eudemon.taurus.app.http;

import java.io.IOException;

public class HttpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		HttpClient.setProxy("202.84.17.41", "8080");
		
		String url = "http://www.thundersplace.org/penis-hangers/hanging.101.v2.html";
		url = "http://xhforum.news.cn/api/debate/addDebate.do";
		String rs = "";
		try {
			 rs = new String(HttpClient.post(url, "userId=jnduan&title=测试 ?&content=测试", "", "utf-8"));
			 System.out.println(rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
