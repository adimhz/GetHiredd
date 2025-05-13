package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.GetHired.model.Company;
import com.GetHired.service.ShowCompanies;

@WebServlet("/company")
public class SearchForCompany extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchForCompany() {
        super();
    }

    // doGet: Loads the full company list when the page is first accessed.
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet called in SearchForCompany");

        ShowCompanies service = new ShowCompanies();
        List<Company> companies = service.getAllCompanies();

        request.setAttribute("companyList", companies);
        request.getRequestDispatcher("/WEB-INF/Pages/SearchForCompany.jsp").forward(request, response);
    }

    // doPost: Handles filtering when the user submits the search form.
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get search parameter from the form
        String searchQuery = request.getParameter("searchQuery");

        // If no search query, return all companies
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            doGet(request, response);
            return;
        }

        // Call the service to get filtered companies based on search query
        ShowCompanies service = new ShowCompanies();
        List<Company> companies = service.getFilteredCompanies(searchQuery);

        // Forward the filtered list to the JSP
        request.setAttribute("companyList", companies);
        request.getRequestDispatcher("/WEB-INF/Pages/SearchForCompany.jsp").forward(request, response);
    }
}