package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dbHelpers.DB;
import dbHelpers.DBProducts;
import dbHelpers.Enums;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/Product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		int UserId = (int) session.getAttribute("UserId");
		int RoleId = (int) session.getAttribute("RoleId");

		if (UserId > 0) {
			DBProducts getProducts = new DBProducts();
			try {
				getProducts.Initialize(DB.getConnection());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				ArrayList<Product> UserProductList = getProducts.GetProducts(UserId,RoleId);
				if (UserId > 0) {
					request.setAttribute("UserProductList", UserProductList);
					request.setAttribute("roledid", RoleId);
					RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");
					dispatcher.forward(request, response);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
