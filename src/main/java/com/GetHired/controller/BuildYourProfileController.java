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
import java.time.LocalDate;

import com.GetHired.model.UserModel;
import com.GetHired.service.BuildYourProfileService;
import com.GetHired.util.ImageUtil;
import com.GetHired.util.ValidationUtil;

@WebServlet("/buildYourProfile")
@MultipartConfig
public class BuildYourProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BuildYourProfileService profileService = new BuildYourProfileService();
    private final ImageUtil imageUtil = new ImageUtil();

    public BuildYourProfileController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            String validation = validateProfileForm(request);
            
            if (validation != null) {
                handleError(request, response, validation);
                return;
            }

            String email = (String) session.getAttribute("username");
            UserModel updatedUser = extractUserModel(request, email);
            boolean isUpdated = profileService.updateUser(updatedUser);

            if (isUpdated) {
                try {
                    if (uploadImage(request)) {
                        handleSuccess(request, response, "Profile updated successfully!", "/WEB-INF/Pages/BuildYourProfile.jsp");
                    } else {
                        handleError(request, response, "Could not upload the image. Please try again later!");
                    }
                } catch (IOException | ServletException e) {
                    handleError(request, response, "An error occurred while uploading the image. Please try again later!");
                    e.printStackTrace();
                }
            } else {
                handleError(request, response, "Failed to update profile. Please try again later!");
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
        req.setAttribute("qualification", req.getParameter("qualification"));
        req.setAttribute("experience", req.getParameter("experience"));
        UserModel user = profileService.getUserDetails((String) req.getSession().getAttribute("username"));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/Pages/BuildYourProfile.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("message", message);
        UserModel user = profileService.getUserDetails((String) req.getSession().getAttribute("username"));
        req.setAttribute("user", user);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    private String validateProfileForm(HttpServletRequest req) {
        String fullName = req.getParameter("fullname");
        String address = req.getParameter("address");
        String contactno = req.getParameter("contactno");
        String dobStr = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String qualification = req.getParameter("qualification");
        String experience = req.getParameter("experience");
        Part image = null;
        try {
            image = req.getPart("profilePic");
        } catch (IOException | ServletException e) {
            return "Error accessing profile picture.";
        }

        // Check for null or empty fields
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
        if (ValidationUtil.isNullOrEmpty(qualification))
            return "Qualification is required.";
        if (ValidationUtil.isNullOrEmpty(experience))
            return "Experience is required.";

        // Convert and validate date of birth
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobStr);
        } catch (Exception e) {
            return "Invalid date format. Please use YYYY-MM-DD.";
        }

        // Validate fields
        if (!ValidationUtil.isValidGender(gender))
            return "Invalid gender selection.";
        if (!ValidationUtil.isValidPhoneNumber(contactno))
            return "Phone number must be 10 digits and start with 98.";
        if (!ValidationUtil.isAgeAtLeast16(dob))
            return "You must be at least 16 years old.";
        if (image != null && image.getSize() > 0 && !ValidationUtil.isValidImageExtension(image))
            return "Invalid image format. Please upload a JPG, JPEG, PNG, or GIF file.";

        return null;
    }

    private UserModel extractUserModel(HttpServletRequest req, String email) throws Exception {
        String fullName = req.getParameter("fullname");
        String address = req.getParameter("address");
        String contactno = req.getParameter("contactno");
        String dobStr = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String qualification = req.getParameter("qualification");
        String experience = req.getParameter("experience");
        Part image = req.getPart("profilePic");
        String imageUrl = imageUtil.getImageNameFromPart(image);

        return new UserModel(email, fullName, gender, address, contactno, dobStr, qualification, experience, imageUrl);
    }

    private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
        Part image = req.getPart("profilePic");
        if (image != null && image.getSize() > 0) {
            return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "userProfileImages");
        }
        return true; // No image provided, consider it successful
    }
}