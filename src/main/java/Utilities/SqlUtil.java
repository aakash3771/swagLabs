package Utilities;

import java.sql.*;
import java.util.Properties;

class SqlUtil {

	private ResultSet resultSet = null;
	private static Statement stmt = null;	
	private static Properties prop = new Properties();
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException
		{
		Class.forName(PropertyReader.readApplicationFile("JDBC_DRIVER"));
		String connectionUrl = PropertyReader.readApplicationFile("DB_URL");

		// Open a connection
			return DriverManager.getConnection(connectionUrl);
	}

	public String executeQuery(String sqlStmn) throws SQLException, ClassNotFoundException {
		// Execute a query
		Statement stmt = getConnection().createStatement();

		// Create and execute a SELECT SQL statement.
		resultSet = stmt.executeQuery(sqlStmn);

		return resultSet.getString(1);
	}

	private static void restoreDatabase() throws Exception {
		
		String databaseName = PropertyReader.readApplicationFile("databaseName");
		String backUpFileName = PropertyReader.readApplicationFile("backUpFileName");
		String dataFile = PropertyReader.readApplicationFile("dataFile");
		String logicalFile = PropertyReader.readApplicationFile("logicalFile");
		try {
			// Execute a query
			Statement stmt = getConnection().createStatement();

			stmt.executeUpdate("RESTORE DATABASE " + databaseName + " FROM DISK = '"+ pathHelpers.currentDir() +"\\DB_Backup\\" + backUpFileName + "'\r\n"
					+ "WITH RECOVERY, " + "MOVE '" + dataFile + "' TO '" + System.getProperty("user.dir") +"\\DB_Backup\\" + dataFile + ".mdf',\r\n"
					+ "MOVE '"+ logicalFile + "' TO '" + System.getProperty("user.dir") +"\\DB_Backup\\" + logicalFile + ".ldf';");
			System.out.println("Database is restored successfully");
			
			// Create and execute a SELECT SQL statement.
			stmt.executeUpdate("ALTER DATABASE " + databaseName + " SET MULTI_USER WITH ROLLBACK IMMEDIATE");
			System.out.println("Multi user mode enabled on Database");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setupDatabase() throws Exception
	{
		dropDatabase();
		restoreDatabase();
	}

	private static void dropDatabase() throws Exception {

		String databaseName = PropertyReader.readApplicationFile("databaseName");
		try {
			Statement stmt = getConnection().createStatement();

			// Create and execute a SELECT SQL statement.
			stmt.executeUpdate("ALTER DATABASE " + databaseName + " SET SINGLE_USER WITH ROLLBACK IMMEDIATE");
			System.out.println("Single user mode enabled on Database");
			
			// Create and execute a SELECT SQL statement.
			stmt.executeUpdate("DROP DATABASE " + databaseName);
			System.out.println("Database is dropped successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
