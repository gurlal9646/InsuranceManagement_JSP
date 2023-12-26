package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import model.User;

public class RegisterUser {
	Connection conn;
	ResultSet rs;
	PreparedStatement statement;
	public RegisterUser() {
		 
		Properties properties = new Properties();
		InputStream inputStream;
		inputStream = getClass().getResourceAsStream("dbconnection.properties");
		
		try {
			properties.load(inputStream);
			inputStream.close();
			
			String driver = properties.getProperty("jdbc.driver");
			
			if(driver != null) {
				Class.forName(driver);
				
				String url = properties.getProperty("jdbc.url");
				String username = properties.getProperty("jdbc.username");
				String password = properties.getProperty("jdbc.password");
               conn = DB.getConnection();
			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("finally")
	public boolean AddUser(User user) throws SQLException {
		boolean status = false;
		String sql = "INSERT INTO user (RoleId, UserName, Password, Cellphone_No,Email,Name,Address,Status) VALUES (?, ?, ?, ?,?,?,?,?)";
	    try {
	    PreparedStatement statement = conn.prepareStatement(sql);

	    statement.setInt(1, user.getRoleId());
	    statement.setString(2, user.getUserName());
	    statement.setString(3, user.getPassword());
	    statement.setString(4, user.getCellphone_No());
	    statement.setString(5, user.getEmail());
	    statement.setString(6, user.getName());
	    statement.setString(7, user.getAddress());
	    statement.setInt(8, user.getStatus());


	    // Use executeUpdate for INSERT, UPDATE, DELETE queries
	    int rowsAffected = statement.executeUpdate();

	    // Optionally, you can check the number of rows affected
	    if (rowsAffected > 0) {
	        status = true;
	    	System.out.println("Insertion successful");
	    } else {
	        System.out.println("Insertion failed");
	    }
	    } catch (SQLException e) {
	    // Handle any SQL exceptions
	    e.printStackTrace();
	    } finally {
	    // Close the PreparedStatement if necessary
	    try {
	        if (statement != null) {
	            statement.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return status;
		
	    }
	}
}