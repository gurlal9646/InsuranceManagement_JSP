package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

import dbHelpers.Enums;
import dbHelpers.RegisterUser;

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/Signup")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterUser registerUser = new RegisterUser();
		
		
		User user = new User();
		
		user.setRoleId(Enums.USER);
		user.setUserName(request.getParameter("UserName"));
		user.setPassword(request.getParameter("Password"));
		user.setCellphone_No(request.getParameter("Cellphone_No"));
		user.setEmail(request.getParameter("Email"));
		user.setName(request.getParameter("Name"));
		user.setAddress(request.getParameter("Address"));
		user.setStatus(Enums.ACTIVE);
		
		try {
			if(registerUser.AddUser(user)) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
