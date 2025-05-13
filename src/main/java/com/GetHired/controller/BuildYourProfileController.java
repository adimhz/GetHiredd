package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.GetHired.model.UserModel;
import com.GetHired.service.BuildYourProfileService;
import com.GetHired.util.ImageUtil;

/**
 * Servlet implementation class BuildYourProfileController
 */
@MultipartConfig
@WebServlet("/buildYourProfile")
public class BuildYourProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BuildYourProfileService profileService = new BuildYourProfileService();
    private final ImageUtil imageUtil = new ImageUtil();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildYourProfileController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login"); // Not logged in
            return;
        }

        String email = (String) session.getAttribute("username");
        UserModel user = profileService.getUserDetails(email);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/Pages/BuildYourProfile.jsp").forward(request, response);
        } else {
            response.getWriter().println("User not found!");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login"); // Not logged in
            return;
        }

        String email = (String) session.getAttribute("username");
        String fullName = request.getParameter("fullname");
        String address = request.getParameter("address");
        String contactno = request.getParameter("contactno");
        String dobStr = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String qualification = request.getParameter("qualification");
        String experience = request.getParameter("experience");
        Part image = request.getPart("profilePic");
		String imageUrl = imageUtil.getImageNameFromPart(image);
		uploadImage(request);

        // Create a UserModel object with the updated details
        UserModel updatedUser = new UserModel(email,fullName, gender,  address,  contactno,  dobStr, experience,  qualification,  imageUrl); // Assuming constructor

        // Call the updateUser() method in the service to update user details
        boolean isUpdated = profileService.updateUser(updatedUser);

        if (isUpdated) {
            request.setAttribute("message", "Profile updated successfully!");
            request.getRequestDispatcher("/WEB-INF/Pages/BuildYourProfile.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Failed to update profile.");
            request.getRequestDispatcher("/WEB-INF/Pages/BuildYourProfile.jsp").forward(request, response);
        }}
        private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
    		Part image = req.getPart("profilePic");
    		return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "userProfileImages");
    	}
    }

