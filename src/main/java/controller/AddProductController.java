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

/**
 * Servlet implementation class AddProductController
 */
@WebServlet("/AddProduct")
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductController() {
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
		HttpSession session = request.getSession();

		int UserId = (int) session.getAttribute("UserId");

		if (UserId > 0) {
			DBProducts getProducts = new DBProducts();
			try {
				getProducts.Initialize(DB.getConnection());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				ArrayList<String> ProductNameList = getProducts.GetCompanyProductsName();
				if (UserId > 0) {
					request.setAttribute("ProductNameList", ProductNameList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("AddProduct.jsp");
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
		String productName = request.getParameter("productName");
		String serialNumber = request.getParameter("serialNumber");
		String purchaseDateStr = request.getParameter("purchaseDate");

		Date purchaseDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			purchaseDate = dateFormat.parse(purchaseDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();

		int UserId = (int) session.getAttribute("UserId");

		Product userProduct = new Product();
		userProduct.setProductName(productName);
		userProduct.setSerialNo(serialNumber);
		userProduct.setPurchaseDate(new Date());
		userProduct.setUserId(UserId);
		DBProducts dbProducts = new DBProducts();
		try {
			dbProducts.Initialize(DB.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			dbProducts.AddUserProduct(userProduct);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Product");
		dispatcher.forward(request, response);
	}

}
