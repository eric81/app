package com.eudemon.taurus.app.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientApache {
	public static String get(String url, String cookie, String encoding) throws IOException {
		String rs = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Cookie", cookie);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				rs = IOUtils.toString(response.getEntity().getContent(), encoding);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		return rs;
	}

	public static String post(String url, String req, String cookie, String encoding) throws IOException {
		String rs = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("code", ""));
			nvps.add(new BasicNameValuePair("data", "c38"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);
			try {
				System.out.println("rs=" + IOUtils.toString(response2.getEntity().getContent(), encoding));
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}

		return rs;
	}

	public static void main(String[] args) {
		try {
			System.out.println(HttpClientApache.get("http://forum.home.news.cn/control", "", "gbk"));
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
