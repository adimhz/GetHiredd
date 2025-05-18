package com.GetHired.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.GetHired.model.jobModel;
import com.GetHired.service.EditJobService;
import com.GetHired.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editjob")
public class EditAJob extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EditJobService jobService = new EditJobService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String jobTitle = request.getParameter("jobTitle");
            String companyName = request.getParameter("companyName");

            // Fetch companies for the dropdown
            request.setAttribute("companies", jobService.getAllCompanies());

            if (!ValidationUtil.isNullOrEmpty(jobTitle) && !ValidationUtil.isNullOrEmpty(companyName)) {
                jobModel job = jobService.getJobDetailsByTitleAndCompany(jobTitle, companyName);
                if (job != null) {
                    request.setAttribute("job", job);
                    request.setAttribute("selectedCompanyName", companyName);
                } else {
                    handleError(request, response, "No job found with title: " + jobTitle + " and company: " + companyName);
                    return;
                }
            }

            request.getRequestDispatcher("/WEB-INF/Pages/EditAJob.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "An unexpected error occurred while loading the job details.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch companies for the dropdown
            request.setAttribute("companies", jobService.getAllCompanies());

            String validation = validateJobForm(request);
            
            if (validation != null) {
                handleError(request, response, validation);
                return;
            }

            jobModel job = extractJobModel(request);
            String companyName = request.getParameter("companyName");
            request.setAttribute("selectedCompanyName", companyName);

            boolean updated = jobService.updateJob(job, companyName);
            if (updated) {
                handleSuccess(request, response, "Job updated successfully!", "/WEB-INF/Pages/EditAJob.jsp");
            } else {
                handleError(request, response, "Failed to update the job. Please ensure the details are correct.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "An unexpected error occurred. Please try again later!");
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        jobModel job = new jobModel();
        job.setJobTitle(req.getParameter("jobTitle"));
        job.setJobType(req.getParameter("jobType"));
        job.setJobDeadline(req.getParameter("jobDeadline"));
        job.setJobQualification(req.getParameter("jobQualification"));
        job.setJobSalary(req.getParameter("jobSalary"));
        job.setJobLocation(req.getParameter("jobLocation"));
        job.setJobDescription(req.getParameter("jobDescription"));
        req.setAttribute("job", job);
        req.setAttribute("selectedCompanyName", req.getParameter("companyName"));
        req.getRequestDispatcher("/WEB-INF/Pages/EditAJob.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    private String validateJobForm(HttpServletRequest req) {
        String jobTitle = req.getParameter("jobTitle");
        String jobType = req.getParameter("jobType");
        String jobDeadline = req.getParameter("jobDeadline");
        String jobQualification = req.getParameter("jobQualification");
        String jobSalary = req.getParameter("jobSalary");
        String jobLocation = req.getParameter("jobLocation");
        String jobDescription = req.getParameter("jobDescription");
        String companyName = req.getParameter("companyName");

        // Check for null or empty fields
        if (ValidationUtil.isNullOrEmpty(jobTitle))
            return "Job title is required.";
        if (ValidationUtil.isNullOrEmpty(jobType))
            return "Job type is required.";
        if (ValidationUtil.isNullOrEmpty(jobDeadline))
            return "Deadline is required.";
        if (ValidationUtil.isNullOrEmpty(jobQualification))
            return "Qualification is required.";
        if (ValidationUtil.isNullOrEmpty(jobSalary))
            return "Salary is required.";
        if (ValidationUtil.isNullOrEmpty(jobLocation))
            return "Location is required.";
        if (ValidationUtil.isNullOrEmpty(jobDescription))
            return "Description is required.";
        if (ValidationUtil.isNullOrEmpty(companyName))
            return "Company name is required.";

        // Validate field formats
        if (!ValidationUtil.isAlphanumericStartingWithLetter(jobTitle))
            return "Job title must start with a letter and be alphanumeric.";
        if (!ValidationUtil.isAlphabetic(jobLocation))
            return "Location should contain only letters.";

        // Validate deadline
        try {
            LocalDate deadlineDate = LocalDate.parse(jobDeadline);
            LocalDate today = LocalDate.now();
            if (deadlineDate.isBefore(today))
                return "Deadline must be today or in the future.";
        } catch (DateTimeParseException e) {
            return "Invalid deadline format. Please use YYYY-MM-DD.";
        }

        // Validate salary
        try {
            int salaryValue = Integer.parseInt(jobSalary);
            if (salaryValue <= 0)
                return "Salary must be a positive number.";
        } catch (NumberFormatException e) {
            return "Salary must be a valid number.";
        }

        return null;
    }

    private jobModel extractJobModel(HttpServletRequest req) {
        jobModel job = new jobModel();
        job.setJobTitle(req.getParameter("jobTitle"));
        job.setJobType(req.getParameter("jobType"));
        job.setJobDeadline(req.getParameter("jobDeadline"));
        job.setJobQualification(req.getParameter("jobQualification"));
        job.setJobSalary(req.getParameter("jobSalary"));
        job.setJobLocation(req.getParameter("jobLocation"));
        job.setJobDescription(req.getParameter("jobDescription"));
        return job;
    }}
