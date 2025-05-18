package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.GetHired.model.Company;
import com.GetHired.model.jobModel;
import com.GetHired.service.DashboardService;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DashboardService service = null;
        try {
            service = new DashboardService();
            int jobsCount = service.getJobsCount();
            jobModel highestSalaryJob = service.getHighestSalaryJob();
            Company mostJobsCompany = service.getCompanyWithMostJobs();

            // Set attributes for JSP
            request.setAttribute("jobsCount", jobsCount);
            request.setAttribute("highestSalaryJob", highestSalaryJob);
            request.setAttribute("mostJobsCompany", mostJobsCompany);

            // Forward to JSP
            request.getRequestDispatcher("/WEB-INF/Pages/AdminDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Error in AdminController (doGet): " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load dashboard data");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DashboardService service = null;
        try {
            service = new DashboardService();
            int jobsCount = service.getJobsCount();
            jobModel highestSalaryJob = service.getHighestSalaryJob();
            Company mostJobsCompany = service.getCompanyWithMostJobs();

            // Set attributes for JSP
            request.setAttribute("jobsCount", jobsCount);
            request.setAttribute("highestSalaryJob", highestSalaryJob);
            request.setAttribute("mostJobsCompany", mostJobsCompany);

            // Forward to JSP
            request.getRequestDispatcher("/WEB-INF/Pages/AdminDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Error in AdminController (doPost): " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load dashboard data");
        }
    }
}