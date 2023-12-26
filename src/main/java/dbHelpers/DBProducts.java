package dbHelpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CompanyProduct;
import model.Product;

public class DBProducts {

	Connection conn;
	ResultSet rs;
	PreparedStatement statement;

	public void Initialize(Connection connection) {
		this.conn = connection;
	}

	@SuppressWarnings("finally")
	public ArrayList<Product> GetProducts(int UserId, int RoleId) throws SQLException {
		ArrayList<Product> response = new ArrayList<Product>();
		try {
			if (RoleId == Enums.USER) {
				String sql = "Select * from Product where UserId = ?";
				statement = conn.prepareStatement(sql);
				statement.setInt(1, UserId);

			} else {
				String sql = "SELECT ProductId, product.UserId, ProductName, SerialNo,PurchaseDate, Name  FROM product\r\n"
						+ "JOIN user ON product.userid = user.userid";
				statement = conn.prepareStatement(sql);
			}

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("ProductId"));
				product.setUserId(rs.getInt("UserId"));
				product.setProductName(rs.getString("ProductName"));
				product.setSerialNo(rs.getString("SerialNo"));
				product.setPurchaseDate(rs.getDate("PurchaseDate"));
				product.setUserName(RoleId == Enums.ADMIN ? rs.getString("Name") : "");
				response.add(product);
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
	public boolean AddUserProduct(Product product) throws SQLException {
		boolean success = false;
		String sql = "INSERT INTO Product (UserId, ProductName, SerialNo, PurchaseDate) VALUES (?, ?, ?, ?)";

		try {

			statement = conn.prepareStatement(sql);
			statement.setInt(1, product.getUserId());
			statement.setString(2, product.getProductName());
			statement.setString(3, product.getSerialNo());
			statement.setDate(4, new java.sql.Date(product.getPurchaseDate().getTime()));

			int rowsAffected = statement.executeUpdate();
			success = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@SuppressWarnings("finally")
	public ArrayList<CompanyProduct> GetCompanyProducts() throws SQLException {
		ArrayList<CompanyProduct> response = new ArrayList<CompanyProduct>();
		String sql = "Select * from CompanyProduct";
		try {
			statement = conn.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				CompanyProduct product = new CompanyProduct();
				product.setCompanyProductId(rs.getInt("CompanyProductId"));
				product.setCompanyProductName(rs.getString("CompanyProductName"));
				product.setCategory(rs.getString("Category"));
				product.setPrice(rs.getFloat("Price"));
				response.add(product);
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
	public boolean AddCompanyProduct(CompanyProduct product) throws SQLException {
		boolean success = false;
		String sql = "INSERT INTO CompanyProduct (CompanyProductName, Category, Price) VALUES (?, ?, ?)";

		try {

			statement = conn.prepareStatement(sql);
			statement.setString(1, product.getCompanyProductName());
			statement.setString(2, product.getCategory());
			statement.setFloat(3, product.getPrice());

			int rowsAffected = statement.executeUpdate();
			success = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@SuppressWarnings("finally")
	public ArrayList<String> GetCompanyProductsName() throws SQLException {
		System.out.println("Inside getcompanyproduct");
		ArrayList<String> response = new ArrayList<String>();
		String sql = "Select CompanyProductName from CompanyProduct";
		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				response.add(rs.getString("CompanyProductName"));
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
