package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.GetHired.model.Company;
import com.GetHired.model.jobModel;
import com.GetHired.service.CreateJobsService;
import com.GetHired.util.ValidationUtil;

@WebServlet("/createjobs")
@MultipartConfig
public class CreateJobs extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateJobs() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/CreateAJob.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String validation = validateJobForm(request);
            
            if (validation != null) {
                handleError(request, response, validation);
                return;
            }

            jobModel job = extractJobModel(request);
            CreateJobsService service = new CreateJobsService();
            Boolean isCreated = service.createJob(job);

            if (isCreated == null) {
                handleError(request, response, "Our server is under maintenance");
            } else if (isCreated) {
                handleSuccess(request, response, "Job added successfully!", "/WEB-INF/Pages/CreateAJob.jsp");
            } else {
                handleError(request, response, "Could not add job. Please try again later!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "An unexpected error occurred. Please try again later!");
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.setAttribute("CreateJobTitle", req.getParameter("CreateJobTitle"));
        req.setAttribute("JobType", req.getParameter("JobType"));
        req.setAttribute("Deadline", req.getParameter("Deadline"));
        req.setAttribute("Qualification", req.getParameter("Qualification"));
        req.setAttribute("Company", req.getParameter("Company"));
        req.setAttribute("Salary", req.getParameter("Salary"));
        req.setAttribute("Location", req.getParameter("Location"));
        req.setAttribute("description", req.getParameter("description"));
        req.getRequestDispatcher("/WEB-INF/Pages/CreateAJob.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    private String validateJobForm(HttpServletRequest req) {
        String title = req.getParameter("CreateJobTitle");
        String type = req.getParameter("JobType");
        String deadline = req.getParameter("Deadline");
        String qualification = req.getParameter("Qualification");
        String companyName = req.getParameter("Company");
        String salary = req.getParameter("Salary");
        String location = req.getParameter("Location");
        String description = req.getParameter("description");

        // Check for null or empty fields
        if (ValidationUtil.isNullOrEmpty(title))
            return "Job title is required.";
        if (ValidationUtil.isNullOrEmpty(type))
            return "Job type is required.";
        if (ValidationUtil.isNullOrEmpty(deadline))
            return "Deadline is required.";
        if (ValidationUtil.isNullOrEmpty(qualification))
            return "Qualification is required.";
        if (ValidationUtil.isNullOrEmpty(companyName))
            return "Company name is required.";
        if (ValidationUtil.isNullOrEmpty(salary))
            return "Salary is required.";
        if (ValidationUtil.isNullOrEmpty(location))
            return "Location is required.";
        if (ValidationUtil.isNullOrEmpty(description))
            return "Description is required.";

        // Validate field formats
        if (!ValidationUtil.isAlphanumericStartingWithLetter(title))
        try {
            int salaryValue = Integer.parseInt(salary);
            if (salaryValue <= 0)
                return "Salary must be a positive number.";
        } catch (NumberFormatException e) {
            return "Salary must be a valid number.";
        }


        return null;
    }

    private jobModel extractJobModel(HttpServletRequest req) {
        String title = req.getParameter("CreateJobTitle");
        String type = req.getParameter("JobType");
        String deadline = req.getParameter("Deadline");
        String qualification = req.getParameter("Qualification");
        String companyName = req.getParameter("Company");
        String salary = req.getParameter("Salary");
        String location = req.getParameter("Location");
        String description = req.getParameter("description");

        Company company = new Company(companyName);
        return new jobModel(title, type, deadline, qualification, company, salary, location, description);
    }
}