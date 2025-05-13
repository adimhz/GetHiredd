package com.GetHired.controller;

import java.io.IOException;


import com.GetHired.model.jobModel;
import com.GetHired.service.EditJobService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@MultipartConfig
@WebServlet("/editjob")
public class EditAJob extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EditJobService jobService = new EditJobService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jobTitle = request.getParameter("jobTitle"); // to allow loading via ?jobTitle=someTitle

        if (jobTitle != null && !jobTitle.trim().isEmpty()) {
            jobModel job = jobService.getJobDetailsByTitle(jobTitle);
            if (job != null) {
                request.setAttribute("job", job);
            } else {
                request.setAttribute("message", "No job found with title: " + jobTitle);
            }
        }

        request.getRequestDispatcher("/WEB-INF/Pages/EditAJob.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        jobModel job = new jobModel();
        job.setJobTitle(request.getParameter("jobTitle"));
        job.setJobType(request.getParameter("jobType"));
        job.setJobDeadline(request.getParameter("jobDeadline"));
        job.setJobQualification(request.getParameter("jobQualification"));
        job.setJobSalary(request.getParameter("jobSalary"));
        job.setJobLocation(request.getParameter("jobLocation"));
        job.setJobDescription(request.getParameter("jobDescription"));

        boolean updated = jobService.updateJob(job);

        if (updated) {
            request.setAttribute("message", "Job updated successfully!");
        } else {
            request.setAttribute("message", "Failed to update the job.");
        }

        request.setAttribute("job", job); // Keep the form filled
        request.getRequestDispatcher("/WEB-INF/Pages/EditAJob.jsp").forward(request, response);
    }
}
