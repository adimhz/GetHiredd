package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.GetHired.service.DeleteCompanyService;
import com.GetHired.util.ValidationUtil;

@WebServlet("/deletecompany")
public class DeleteCompany extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DeleteCompanyService deleteCompanyService = new DeleteCompanyService();

    public DeleteCompany() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/DeleteCompany.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String validation = validateDeleteForm(request);
            
            if (validation != null) {
                handleError(request, response, validation);
                return;
            }

            String companyName = request.getParameter("CompanyName");
            String companyContact = request.getParameter("CompanyContact");

            boolean isDeleted = deleteCompanyService.deleteCompany(companyName, companyContact);

            if (isDeleted) {
                handleSuccess(request, response, "Company deleted successfully.", "/WEB-INF/Pages/DeleteCompany.jsp");
            } else {
                handleError(request, response, "Failed to delete company. Please ensure the details are correct.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "An unexpected error occurred. Please try again later!");
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.setAttribute("CompanyName", req.getParameter("CompanyName"));
        req.setAttribute("CompanyContact", req.getParameter("CompanyContact"));
        req.getRequestDispatcher("/WEB-INF/Pages/DeleteCompany.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    private String validateDeleteForm(HttpServletRequest req) {
        String companyName = req.getParameter("CompanyName");
        String companyContact = req.getParameter("CompanyContact");

        // Check for null or empty fields
        if (ValidationUtil.isNullOrEmpty(companyName))
            return "Company name is required.";
        if (ValidationUtil.isNullOrEmpty(companyContact))
            return "Company contact is required.";

        // Validate field formats
        if (!ValidationUtil.isValidPhoneNumber(companyContact))
            return "Company contact number must be 10 digits and start with 98.";

        return null;
    }
}