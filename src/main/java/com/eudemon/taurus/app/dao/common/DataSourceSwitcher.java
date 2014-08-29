package com.eudemon.taurus.app.dao.common;


public class DataSourceSwitcher {
	public static void reset(){
		DbContextHolder.setDataSource("d0");
	}
	
	public static void toRead(){
		DbContextHolder.setDataSource("r1");
	}
	
	public static void toWrite(){
		DbContextHolder.setDataSource("d0");
	}
}
