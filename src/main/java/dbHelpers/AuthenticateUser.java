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

public class AuthenticateUser {
	Connection conn;
	ResultSet rs;
	PreparedStatement statement;

	public void Initialize(Connection connection) {
		this.conn = connection;
	}

	@SuppressWarnings("finally")
	public User ValidateUser(User user) throws SQLException {
		User response = new User();
		String sql = "Select UserId, RoleId, Name from user where UserName = ? or Email=? AND Password=?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getUserName());
			st.setString(2, user.getEmail());
			st.setString(3, user.getPassword());

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				response.setUserId(rs.getInt("UserId"));
				response.setRoleId(rs.getInt("RoleId"));
				response.setName(rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return response;

		}
	}
}