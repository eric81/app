package com.eudemon.taurus.app.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;

public class SystemUtil {

	/**
	 * 判断当前操作是否Windows.
	 * 
	 * @return true---是Windows操作系统
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本机ip地址，并自动区分Windows还是linux操作系统
	 * @return String 
	 */
	public static String getLocalIP() {
		String sIP = "";
		InetAddress ip = null;
		try {
			//如果是Windows操作系统
			if (isWindowsOS()) {
				ip = InetAddress.getLocalHost();
			}
			//如果是Linux操作系统
			else {
				boolean bFindIP = false;
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
						.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					if (bFindIP) {
						break;
					}
					NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
					//----------特定情况，可以考虑用ni.getName判断
					//遍历所有ip
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						ip = (InetAddress) ips.nextElement();
						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() //127.开头的都是lookback地址
								&& ip.getHostAddress().indexOf(":") == -1) {
							bFindIP = true;
							break;
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}
	
	public static boolean isProcessRunningOnWindows(String processName) throws IOException {
		Process p = Runtime.getRuntime().exec("cmd /c tasklist ");
		String taskStr = IOUtils.toString(p.getInputStream());

		if (taskStr.indexOf(processName) > 1) {
			return true;
		}

		return false;
	}
	
	public static void main(String[] args) {
		String serverIP = SystemUtil.getLocalIP();
		System.out.println("serverIP:" + serverIP);
		System.out.println("Java version:" + SystemUtils.JAVA_RUNTIME_VERSION);
		
		DefaultExecutor executor = new DefaultExecutor();
		try {
			int exiValue = executor.execute(CommandLine.parse("cmd /c java -version"));
			System.out.println("exiValue : " + exiValue);
			ExecuteWatchdog dog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
			executor.setWatchdog(dog);
			ExecuteStreamHandler hander = new PumpStreamHandler();
			executor.setStreamHandler(hander);
			System.out.println(hander.toString());
		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}