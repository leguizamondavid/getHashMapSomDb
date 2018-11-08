package com.amdocs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class JDBCUtil {

	private static final long WAIT_DB_RETRY = 10;
	private static final int SLEEP_DB_RETRY = 3;

	private static Connection getConnection(String dbUrl, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	return con;

	}

	private static Connection getConnectionWithRetry(String dbUrl, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
		int attempt = 0;
		Connection con = getConnection(dbUrl, dbUser, dbPassword);
		while (con == null && attempt < SLEEP_DB_RETRY) {
			attempt++;
			System.out.println("Connection failed");
			try {
				TimeUnit.SECONDS.sleep(WAIT_DB_RETRY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			con = getConnection(dbUrl, dbUser, dbPassword);
		}
		return con;
	}

	public static Connection getSourceSOMDBConnection() throws SQLException, ClassNotFoundException {
		RemoteEnvDetails remDetails = RemoteEnvDetails.getInstance();

		Connection con = getConnectionWithRetry(remDetails.getDbUrl(), remDetails.getDbUser(),
				remDetails.getDbPassword());
		return con;
	}
	
	public static Connection getTargetSOMDBConnection() throws SQLException, ClassNotFoundException {
		RemoteEnvDetails remDetails = RemoteEnvDetails.getInstance();

		Connection con = getConnectionWithRetry(remDetails.getTargetDbUrl(), remDetails.getTargetDbUser(),
				remDetails.getTargetDbPassword());
		return con;
	}

}
