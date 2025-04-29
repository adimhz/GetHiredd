package com.GetHired.controller;

import com.GetHired.service.RegisterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.GetHired.model.UserModel;
import com.GetHired.util.ImageUtil;
import com.GetHired.util.PasswordUtil;
import com.GetHired.util.ValidationUtil;

@WebServlet("/register")
@MultipartConfig
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ImageUtil imageUtil = new ImageUtil();
    public Register() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String validation = validateRegistrationForm(request);
            
            if (validation != null) {
                handleError(request, response, validation);
                return;
            }
            
            UserModel user = extractUserModel(request);
            RegisterService service = new RegisterService();
            Boolean isRegistered = service.registerUser(user);

            if (isRegistered == null) {
                handleError(request, response, "Our server is under maintenance");
            }
            else if (isRegistered) {
            	try {
					if (uploadImage(request)) {
						handleSuccess(request, response, "Your account is successfully created!", "/WEB-INF/Pages/Login.jsp");
					} else {
						handleError(request, response, "Could not upload the image. Please try again later!");
					}
            	}
            catch (IOException | ServletException e) {
				handleError(request, response, "An error occurred while uploading the image. Please try again later!");
				e.printStackTrace(); // Log the exception
			}}
    
            else {
                handleError(request, response, "Could not register your account. Please try again later!");
            }
        } catch (Exception e) {
            handleError(request, response, "An unexpected error occurred. Please try again later!");
            e.printStackTrace();
        }
    }
    
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.setAttribute("fullname", req.getParameter("fullname"));
        req.setAttribute("address", req.getParameter("address"));
        req.setAttribute("contactno", req.getParameter("contactno"));
        req.setAttribute("dob", req.getParameter("dob"));
        req.setAttribute("gender", req.getParameter("gender"));
        req.setAttribute("Email", req.getParameter("Email"));
        req.setAttribute("qualification", req.getParameter("qualification"));
        req.setAttribute("experience", req.getParameter("experience"));
        req.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    private String validateRegistrationForm(HttpServletRequest req) {
        String fullName = req.getParameter("fullname");
        String address = req.getParameter("address");
        String contactno = req.getParameter("contactno");
        String dobStr = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String email = req.getParameter("Email");
        String qualification = req.getParameter("qualification");
        String experience = req.getParameter("experience");
        String password = req.getParameter("password");
        String retypePassword = req.getParameter("reconfirmPassword");

        // Check for null or empty fields first
        if (ValidationUtil.isNullOrEmpty(fullName))
            return "Full Name is required.";
        if (ValidationUtil.isNullOrEmpty(address))
            return "Address is required.";
        if (ValidationUtil.isNullOrEmpty(contactno))
            return "Contact number is required.";
        if (ValidationUtil.isNullOrEmpty(dobStr))
            return "Date of birth is required.";
        if (ValidationUtil.isNullOrEmpty(gender))
            return "Gender is required.";
        if (ValidationUtil.isNullOrEmpty(email))
            return "Email is required.";
        if (ValidationUtil.isNullOrEmpty(qualification))
            return "Qualification is required.";
        if (ValidationUtil.isNullOrEmpty(experience))
            return "Experience is required.";
        if (ValidationUtil.isNullOrEmpty(password))
            return "Password is required.";
        if (ValidationUtil.isNullOrEmpty(retypePassword))
            return "Please retype the password.";

        // Convert date of birth
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobStr);
        } catch (Exception e) {
            return "Invalid date format. Please use YYYY-MM-DD.";
        }

        // Validate fields
        if (!ValidationUtil.isValidGender(gender))
            return "Invalid gender selection.";
        if (!ValidationUtil.isValidEmail(email))
            return "Invalid email format.";
        if (!ValidationUtil.isValidPhoneNumber(contactno))
            return "Phone number must be 10 digits and start with 98.";
        if (!ValidationUtil.isValidPassword(password))
            return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
        if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
            return "Passwords do not match.";

        // Check if the date of birth is at least 16 years before today
        if (!ValidationUtil.isAgeAtLeast16(dob))
            return "You must be at least 16 years old to register.";

        return null;
    }

    private UserModel extractUserModel(HttpServletRequest req) throws Exception {
        String fullName = req.getParameter("fullname");
        String address = req.getParameter("address");
        String contactno = req.getParameter("contactno");
        String dobStr = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String email = req.getParameter("Email");
        String qualification = req.getParameter("qualification");
        String experience = req.getParameter("experience");
        String password = req.getParameter("password");


        password = PasswordUtil.encrypt(email, password);
        Part image = req.getPart("profilePic");
		String imageUrl = imageUtil.getImageNameFromPart(image);

        UserModel user=new UserModel(email, fullName, gender, address, contactno, dobStr, qualification, experience, password,imageUrl);
        return user;
       
    }
    private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("profilePic");
		return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "userProfileImages");
	}
}