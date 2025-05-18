package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.GetHired.service.DeleteJobService;
import com.GetHired.util.ValidationUtil;

@WebServlet("/deletejobs")
public class DeleteAJob extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteAJob() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/DeleteAJob.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String validation = validateDeleteForm(request);
            
            if (validation != null) {
                handleError(request, response, validation);
                return;
            }

            String jobTitle = request.getParameter("deleteJobTitle");
            String companyName = request.getParameter("deleteCompany");

            DeleteJobService service = new DeleteJobService();
            boolean isDeleted = service.deleteJob(jobTitle, companyName);

            if (isDeleted) {
                handleSuccess(request, response, "Job deleted successfully.", "/WEB-INF/Pages/DeleteAJob.jsp");
            } else {
                handleError(request, response, "Job not found or could not be deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "An unexpected error occurred. Please try again later!");
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.setAttribute("deleteJobTitle", req.getParameter("deleteJobTitle"));
        req.setAttribute("deleteCompany", req.getParameter("deleteCompany"));
        req.getRequestDispatcher("/WEB-INF/Pages/DeleteAJob.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    private String validateDeleteForm(HttpServletRequest req) {
        String jobTitle = req.getParameter("deleteJobTitle");
        String companyName = req.getParameter("deleteCompany");

        // Check for null or empty fields
        if (ValidationUtil.isNullOrEmpty(jobTitle))
            return "Job title is required.";
        if (ValidationUtil.isNullOrEmpty(companyName))
            return "Company name is required.";

        return null;
    }
}