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
import java.util.ArrayList;

import dbHelpers.DB;
import dbHelpers.DBProducts;

/**
 * Servlet implementation class CompanyProductController
 */
@WebServlet("/CompanyProduct")

public class CompanyProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyProductController() {
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
		int RoleId = (int) session.getAttribute("RoleId");

		System.out.println(RoleId);

		if (UserId > 0) {
			DBProducts getProducts = new DBProducts();
			try {
				getProducts.Initialize(DB.getConnection());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				ArrayList<CompanyProduct> CompanyProductList = getProducts.GetCompanyProducts();
				if (UserId > 0) {
					request.setAttribute("CompanyProductList", CompanyProductList);
					request.setAttribute("roledid", RoleId);
					RequestDispatcher dispatcher = request.getRequestDispatcher("CompanyProduct.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
