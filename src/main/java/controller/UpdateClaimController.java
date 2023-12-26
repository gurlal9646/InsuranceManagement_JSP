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
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dbHelpers.DB;
import dbHelpers.DBClaims;
import dbHelpers.DBProducts;
import dbHelpers.Enums;
import dbHelpers.RegisterUser;

/**
 * Servlet implementation class UpdatClaimController
 */
@WebServlet("/UpdateClaim")
public class UpdateClaimController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateClaimController() {
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

		int ClaimId = Integer.parseInt(request.getParameter("claimId"));
		int Status = Integer.parseInt(request.getParameter("statusRadios"));

		HttpSession session = request.getSession();

		int UserId = (int) session.getAttribute("UserId");

		if (UserId > 0) {
			DBClaims dbClaims = new DBClaims();
			try {
				dbClaims.Initialize(DB.getConnection());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				boolean result = dbClaims.UpdateClaimStatus(ClaimId, Status);
				if (result) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("Claim");
					dispatcher.forward(request, response);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
