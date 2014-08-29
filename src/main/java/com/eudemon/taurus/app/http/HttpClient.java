package com.eudemon.taurus.app.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;

public class HttpClient {
	public static byte[] get(String urlStr, String cookie, String encoding) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
		conn.setRequestProperty("Charsert", encoding);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setConnectTimeout(0);
		conn.setRequestProperty("Cookie", cookie);
		conn.connect();

		InputStream is = conn.getInputStream();
		return IOUtils.toByteArray(is);
	}

	public static byte[] post(String urlStr, String req, String cookie, String encoding) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
		conn.setRequestProperty("Charsert", encoding);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setConnectTimeout(0);
		conn.setRequestProperty("Cookie", cookie);
		conn.connect();

		OutputStream out = conn.getOutputStream();
		Charset.forName(encoding).encode(req);
		out.write(req.getBytes(encoding));
		out.close();

		InputStream is = conn.getInputStream();
		return IOUtils.toByteArray(is);
	}

	public static void setProxy(String host, String port) {
		Properties prop = System.getProperties();
		prop.put("proxySet", "true");
		prop.setProperty("http.proxyHost", host);
		prop.setProperty("http.proxyPort", port);
	}
	
	public void executeJS(String js, String functionName, Object... parameter) {
		try {
			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("JavaScript");
			engine.eval(js);
			Invocable inv = (Invocable) engine;
			String value = (String) inv.invokeFunction(functionName, parameter);
			System.out.println(value);
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
