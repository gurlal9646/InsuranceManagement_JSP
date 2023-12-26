package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Claim;
import model.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dbHelpers.DB;
import dbHelpers.DBProducts;
import dbHelpers.DBClaims;
import dbHelpers.Enums;

/**
 * Servlet implementation class AddClaim
 */
@WebServlet("/AddClaim")
public class AddClaimController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClaimController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Doget Claim controller");
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
				ArrayList<Product> productList = getProducts.GetProducts(UserId, RoleId);
				if (UserId > 0) {
					request.setAttribute("productList", productList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("AddClaim.jsp");
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

		int productId = Integer.parseInt(request.getParameter("productId"));
		String claimDescription = request.getParameter("claimDescription");
		String claimDateStr = request.getParameter("claimDate");

		Date claimDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			claimDate = dateFormat.parse(claimDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();

		int UserId = (int) session.getAttribute("UserId");

		Claim claim = new Claim();
		claim.setProductId(productId);
		claim.setClaimDate(claimDate);
		claim.setDescription(claimDescription);
		claim.setUserId(UserId);
		claim.setStatus(Enums.CLAIMPENDING);
		DBClaims dbClaims = new DBClaims();
		try {
			dbClaims.Initialize(DB.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			if (dbClaims.GetClaimsCount(UserId, productId) >= 3) {
				request.setAttribute("error", "Already three claims registered for the product");
				RequestDispatcher dispatcher = request.getRequestDispatcher("AddClaim.jsp");
				dispatcher.forward(request, response);
			} else {
				dbClaims.AddClaim(claim);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Claim");
				dispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Claim");
	}

}
