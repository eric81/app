package com.eudemon.taurus.app.dao;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import com.eudemon.taurus.app.common.AppCtxServer;
import com.eudemon.taurus.app.common.Config;
import com.eudemon.taurus.app.common.Log;
import com.eudemon.taurus.app.util.ScriptRunner;
import com.eudemon.taurus.app.util.SystemUtil;

public class DatabaseService {
	public static boolean isDemonstarted = false;

	public static void startMySqlOnWindows() throws IOException, InterruptedException {
		Log.getInfoLogger().info("Starting MySQL ......");
		if (SystemUtil.isProcessRunningOnWindows("mysqld")) {
			Log.getInfoLogger().info("MySQL already started");
			return;
		} else {
			Runtime.getRuntime().exec("mysql/bin/mysqld.exe");
		}

		do {
			if (SystemUtil.isProcessRunningOnWindows("mysqld")) {
				Log.getInfoLogger().info("MySQL start success!");
				break;
			} else {
				Log.getInfoLogger().info("MySQL not started, another check will start after 5 seconds");
				Thread.sleep(5 * 1000);
			}
		} while (true);
	}

	private static void startMysql(String mysqlhome, String dbServerName,
			int dbPort) throws Exception {
		String osName = System.getProperty("os.name").toLowerCase().trim();
		String path = "";
		String commandToExecute = "";
		if (osName.indexOf("windows") != -1) {
			path = path.replace('/', '\\');
			mysqlhome = mysqlhome.replace('/', '\\');
			commandToExecute = (new StringBuilder()).append("cmd /c \"\"")
					.append(path).append("\\bin\\startMySQL\" \"")
					.append(mysqlhome).append("\"\"").toString();
		} else if (osName.equals("sunos") || osName.startsWith("linux"))
			commandToExecute = (new StringBuilder()).append("sh ").append(path)
					.append("/bin/startMySQL.sh ").append(mysqlhome).toString();
		else if (osName.indexOf("95") >= 0 || osName.indexOf("98") >= 0) {
			path = path.replace('/', '\\');
			mysqlhome = mysqlhome.replace('/', '\\');
			commandToExecute = (new StringBuilder())
					.append("command.com /c \"").append(path)
					.append("\\bin\\startMySQL\" ").append("\"")
					.append(mysqlhome).append("\"").toString();
		}
		runCommand(commandToExecute);
		sleep(1000L);
		checkDBStarted(dbServerName, dbPort, "Unable to start Mysql daemon");
	}
	
	public static void createDatabase(){
		try {
			DataSource ds = (DataSource) AppCtxServer.getInstance().getBean("dynamicDataSource");
			Connection conn = ds.getConnection();
			
			ScriptRunner sr = new ScriptRunner(conn);
			sr.setAutoCommit(true);
			sr.setErrorLogWriter(new PrintWriter(Config.getString("log.path") + "create_database_error.log", Config.getString("app.encoding")));
			sr.setLogWriter(new PrintWriter(Config.getString("log.path") + "create_database.log", Config.getString("app.encoding")));
			
			Resource resource = new DefaultResourceLoader().getResource("database/create.sql");
			InputStreamReader reader = new InputStreamReader(resource.getInputStream(), Config.getString("app.encoding"));
			
			sr.runScript(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void runCommand(String cmd) throws Exception {
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
	}

	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception ex) {
		}
	}

	private static void checkDBStarted(String dbServerName, int dbPort,
			String message) {
		Socket socket = null;
		int count = 0;
		do {
			if (count >= 20)
				break;
			try {
				socket = new Socket(dbServerName, dbPort);
				isDemonstarted = true;
				break;
			} catch (Exception es) {
				sleep(300L);
				count++;
			}
		} while (true);
		closeResources(null, null, socket);
		if (!isDemonstarted) {
			System.out.println(message);
		}
	}

	private static void closeResources(Connection con, Statement statement,
			Socket socket) {
		try {
			if (statement != null)
				statement.close();
		} catch (Exception e) {
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
		}
		try {
			if (socket != null)
				socket.close();
		} catch (Exception e) {
		}
	}

	public static void main(String args[]) {
		try {
			startMysql("D:/program/apache-tomcat-6.0.35", "MySQL", 3306);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
