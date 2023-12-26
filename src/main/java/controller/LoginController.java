package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

import dbHelpers.Enums;
import dbHelpers.AuthenticateUser;
import dbHelpers.DB;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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
		AuthenticateUser authenticateUser = new AuthenticateUser();
		try {
			authenticateUser.Initialize(DB.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}

		User user = new User();

		user.setRoleId(Enums.USER);
		user.setUserName(request.getParameter("usernameEmail"));
		user.setEmail(request.getParameter("usernameEmail"));
		user.setPassword(request.getParameter("password"));

		try {
			User userResponse = authenticateUser.ValidateUser(user);
			if (userResponse.getUserId() > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("UserId", userResponse.getUserId());
				session.setAttribute("RoleId", userResponse.getRoleId());
				session.setAttribute("username", userResponse.getName());
				if (userResponse.getRoleId() == Enums.USER) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("Product");
					dispatcher.forward(request, response);
				} else if (userResponse.getRoleId() == Enums.ADMIN) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("CompanyProduct");
					dispatcher.forward(request, response);
				}

			}
			else {
				request.setAttribute("error", "Username or password is incorrect");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
