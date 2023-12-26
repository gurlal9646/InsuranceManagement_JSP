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
import java.util.ArrayList;

import dbHelpers.DB;
import dbHelpers.DBClaims;
import dbHelpers.Enums;

/**
 * Servlet implementation class ClaimController
 */
@WebServlet("/Claim")

public class ClaimController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClaimController() {
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

		if (UserId > 0) {
			DBClaims getClaims = new DBClaims();
			try {
				getClaims.Initialize(DB.getConnection());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				ArrayList<Claim> claimList = getClaims.GetClaims(UserId, RoleId);
				request.setAttribute("claimList", claimList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Claim.jsp");
				dispatcher.forward(request, response);

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
