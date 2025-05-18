package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.GetHired.model.Company;
import com.GetHired.service.CreateCompanyService;
import com.GetHired.util.ValidationUtil;

@WebServlet(name = "CreateCompany", urlPatterns = { "/createCompany" })
@MultipartConfig
public class CreateCompany extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateCompany() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/CreateACompany.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String validation = validateCompanyForm(request);
            
            if (validation != null) {
                handleError(request, response, validation);
                return;
            }

            Company company = extractCompanyModel(request);
            CreateCompanyService service = new CreateCompanyService();
            Boolean isCreated = service.createcompany(company);

            if (isCreated == null) {
                handleError(request, response, "Our server is under maintenance");
            } else if (isCreated) {
                handleSuccess(request, response, "Company added successfully!", "/WEB-INF/Pages/CreateACompany.jsp");
            } else {
                handleError(request, response, "Could not add company. Please try again later!");
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
        req.setAttribute("CompanyLocation", req.getParameter("CompanyLocation"));
        req.setAttribute("CompanyContact", req.getParameter("CompanyContact"));
        req.getRequestDispatcher("/WEB-INF/Pages/CreateACompany.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    private String validateCompanyForm(HttpServletRequest req) {
        String companyName = req.getParameter("CompanyName");
        String companyLocation = req.getParameter("CompanyLocation");
        String companyContact = req.getParameter("CompanyContact");

        // Check for null or empty fields
        if (ValidationUtil.isNullOrEmpty(companyName))
            return "Company Name is required.";
        if (ValidationUtil.isNullOrEmpty(companyLocation))
            return "Company Location is required.";
        if (ValidationUtil.isNullOrEmpty(companyContact))
            return "Company Contact is required.";

        // Validate fields
        if (!ValidationUtil.isValidPhoneNumber(companyContact))
            return "Company contact number must be 10 digits and start with 98.";

        return null;
    }

    private Company extractCompanyModel(HttpServletRequest req) {
        String companyName = req.getParameter("CompanyName");
        String companyLocation = req.getParameter("CompanyLocation");
        String companyContact = req.getParameter("CompanyContact");

        return new Company(companyName, companyLocation, companyContact);
    }
}