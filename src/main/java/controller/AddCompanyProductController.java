package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CompanyProduct;
import model.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dbHelpers.DB;
import dbHelpers.DBProducts;

/**
 * Servlet implementation class AddCompanyProductController
 */
@WebServlet("/AddCompanyProduct")

public class AddCompanyProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCompanyProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productName = request.getParameter("productName");
		String category = request.getParameter("category");
		Float price = Float.parseFloat(request.getParameter("price"));

		CompanyProduct companyProduct = new CompanyProduct();
		companyProduct.setCompanyProductName(productName);
		companyProduct.setCategory(category);
		companyProduct.setPrice(price);
		DBProducts dbProducts = new DBProducts();
		try {
			dbProducts.Initialize(DB.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			dbProducts.AddCompanyProduct(companyProduct);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("CompanyProduct");
		dispatcher.forward(request, response);
	}

}
