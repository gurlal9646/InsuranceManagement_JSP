package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
public class DB {
	private static Connection connection;


	public static Connection getConnection() throws Exception {
		Properties properties = new Properties();
		InputStream inputStream;
		inputStream = DB.class.getResourceAsStream("dbconnection.properties");
		
		try {
			properties.load(inputStream);
			inputStream.close();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (connection == null || connection.isClosed()) {
			Class.forName(properties.getProperty("jdbc.driver"));
			connection = DriverManager.getConnection(properties.getProperty("jdbc.url"),properties.getProperty("jdbc.username"),properties.getProperty("jdbc.password"));
		}
		return connection;
	}

}
