package dbHelpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CompanyProduct;
import model.Claim;

public class DBClaims {

	Connection conn;
	ResultSet rs;
	PreparedStatement statement;

	public void Initialize(Connection connection) {
		this.conn = connection;
	}

	@SuppressWarnings("finally")
	public ArrayList<Claim> GetClaims(int UserId, int RoleId) throws SQLException {
		ArrayList<Claim> response = new ArrayList<Claim>();

		try {
			if (RoleId == Enums.USER) {

				String sql = "SELECT product.ProductName, claim.ClaimId, claim.ClaimDate, claim.Description, claim.Status\r\n"
						+ "FROM claim\r\n"
						+ "JOIN product ON product.productid = claim.productid where claim.userid = ?";
				statement = conn.prepareStatement(sql);
				statement.setInt(1, UserId);
			} else if ((RoleId == Enums.ADMIN)) {
				String sql = "SELECT Name, ProductName, ClaimId, ClaimDate, Description, claim.Status\r\n"
						+ "						FROM product JOIN claim ON product.productid = claim.productid\r\n"
						+ "						JOIN user on claim.userid = user.userid";
				statement = conn.prepareStatement(sql);
			}

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Claim claim = new Claim();
				claim.setUserName(RoleId == Enums.ADMIN ? rs.getString("Name") : "");
				claim.setClaimId(rs.getInt("ClaimId"));
				claim.setProductName(rs.getString("ProductName"));
				claim.setClaimDate(rs.getDate("ClaimDate"));
				claim.setDescription(rs.getString("Description"));
				claim.setStatus(rs.getInt("Status"));
				response.add(claim);
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

	@SuppressWarnings("finally")
	public boolean AddClaim(Claim claim) throws SQLException {
		boolean success = false;
		String sql = "INSERT INTO claim (UserId, ProductId, ClaimDate, Description, Status) VALUES (?, ?, ?, ?,?)";

		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, claim.getUserId());
			statement.setInt(2, claim.getProductId());
			statement.setDate(3, new java.sql.Date(claim.getClaimDate().getTime()));
			statement.setString(4, claim.getDescription());
			statement.setInt(5, claim.getStatus());

			int rowsAffected = statement.executeUpdate();
			success = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@SuppressWarnings("finally")
	public boolean UpdateClaimStatus(int ClaimId, int Status) throws SQLException {
		boolean success = false;
		String sql = "UPDATE claim set status = ? where claimid = ?";

		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, Status);
			statement.setInt(2, ClaimId);
			int rowsAffected = statement.executeUpdate();
			success = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}
	
	@SuppressWarnings("finally")
	public int GetClaimsCount(int UserId, int ProductId) throws SQLException {
		int count = 0;
		String sql = "select count(claimId) AS ClaimCount from claim where userid = ? and productid = ?";

		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, UserId);
			statement.setInt(2, ProductId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				count = rs.getInt("ClaimCount");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

}
