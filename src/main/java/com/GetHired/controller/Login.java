package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;


import com.GetHired.service.loginService;
import com.GetHired.util.CookieUtil;
import com.GetHired.util.SessionUtil;
import com.GetHired.model.UserModel;
@WebServlet("/login")
	public class Login extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private final loginService loginService;

	    public Login() {
	        this.loginService = new loginService();
	    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the login page
    	 request.getRequestDispatcher("/WEB-INF/Pages/Login.jsp").forward(request, response);
    }

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    String Email = request.getParameter("Email");
    	    String password = request.getParameter("password");

    	    UserModel user = new UserModel(Email, password);
    	  

    	    Boolean loginStatus = loginService.loginUser(user);
    	  

    	    if (Boolean.TRUE.equals(loginStatus)) {
    	    	SessionUtil.setAttribute(request, "username", Email); // for consistency
    	    	UserModel fullUser = loginService.getUserDetails(Email);
                if (fullUser != null) {
                    SessionUtil.setAttribute(request, "name", fullUser.getFullName());
                    SessionUtil.setAttribute(request, "profilePicture", fullUser.getImageUrl() != null ? fullUser.getImageUrl() : "default.jpg");
                  
                }
    	    	if (Email.equals("Admin@gmail.com")) {
    				CookieUtil.addCookie(response, "role", "admin", 5 * 30);
    				request.setAttribute("success", "Login successful!");
    				request.getRequestDispatcher("/WEB-INF/Pages/AdminDashboard.jsp").forward(request, response);
    	    } else {
    	    	CookieUtil.addCookie(response, "role", "user", 5); 				
    	    	request.getRequestDispatcher("/WEB-INF/Pages/SearchForJob.jsp").forward(request, response);
				System.out.println("Login sucess");
				
			}}
    			else {
    	        System.out.println("Login failed");
    	        handleLoginFailure(request, response, loginStatus);
    	    }


    	    
    	        }
 private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		req.setAttribute("error", errorMessage);
		req.getRequestDispatcher("/WEB-INF/Pages/Login.jsp").forward(req, resp);
	}
    	    }
     

   
