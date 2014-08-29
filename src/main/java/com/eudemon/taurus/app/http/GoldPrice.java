package com.eudemon.taurus.app.http;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class GoldPrice {
	
	public double getPriceDollar(){
		String rs = "";
		try {
			rs = new String(HttpClient.get("http://www.icbc.com.cn/ICBCDynamicSite/Charts/TimeLine.aspx?pWidth=1010&pHeight=600&dataType=0&dataId=801&picType=3", "", "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(rs);
		String strPrice = StringUtils.substringAfterLast(rs, "<span id=\"cell1\" style=\"color: Red\">");
		if(null == strPrice || strPrice.equals("")){
			strPrice = StringUtils.substringAfterLast(rs, "<span id=\"cell1\" style=\"color: #008000\">");
		}
		strPrice = StringUtils.substring(strPrice, 58, 70);
		
		double price = 0;
		try {
			price = Double.parseDouble(strPrice);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return price;
	}
	
	public double getPriceRMB(){
		String rs = "";
		try {
			rs = new String(HttpClient.get("http://www.icbc.com.cn/ICBCDynamicSite/Charts/TimeLine.aspx?pWidth=1010&pHeight=600&dataType=0&dataId=901&picType=3", "", "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String strPrice = StringUtils.substringAfterLast(rs, "<span id=\"cell1\" style=\"color: Red\">");
		if(null == strPrice || strPrice.equals("")){
			strPrice = StringUtils.substringAfterLast(rs, "<span id=\"cell1\" style=\"color: #008000\">");
		}
		strPrice = StringUtils.substring(strPrice, 58, 70);
		
		double price = 0;
		try {
			price = Double.parseDouble(strPrice);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return price;
	}
	
	
	public String news(){
		String url = "http://live.sina.com.cn/zt/l/v/finance/globalnews1/?bsh_bid=361236833";
		String rs = "";
		try {
			 rs = new String(HttpClient.get(url, "", "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			HttpClient.setProxy("202.84.17.41", "8080");
			GoldPrice p = new GoldPrice();
			while(true){
				System.out.println("U.S:" + p.getPriceDollar() + ", RMB:" + p.getPriceRMB());
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}

}
