package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.GetHired.model.Company;
import com.GetHired.model.jobModel;
import com.GetHired.service.CreateJobsService;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            CreateJobsService service = new CreateJobsService();
            List<jobModel> jobs = service.getAllJobs();
            int jobsCount = service.getJobsCount();
            jobModel highestSalaryJob = service.getHighestSalaryJob();
            Company mostJobsCompany = service.getCompanyWithMostJobs();

            System.out.println("Jobs count: " + jobsCount);
            System.out.println("Highest Salary Job: " + (highestSalaryJob != null ? highestSalaryJob.getJobTitle() : "null"));
            System.out.println("Most Jobs Company: " + (mostJobsCompany != null ? mostJobsCompany.getCompanyName() : "null"));

            // Manually construct JSON response
            StringBuilder json = new StringBuilder("{");
            json.append("\"jobs\":[");
            if (jobs != null) {
                for (int i = 0; i < jobs.size(); i++) {
                    jobModel job = jobs.get(i);
                    json.append("{")
                        .append("\"jobTitle\":\"").append(escapeJson(job.getJobTitle())).append("\",")
                        .append("\"companyName\":\"").append(escapeJson(job.getJobCompanyId().getCompanyName())).append("\",")
                        .append("\"jobType\":\"").append(escapeJson(job.getJobType() != null ? job.getJobType() : "")).append("\",")
                        .append("\"jobSalary\":\"").append(escapeJson(job.getJobSalary() != null ? job.getJobSalary() : "")).append("\",")
                        .append("\"jobLocation\":\"").append(escapeJson(job.getJobLocation() != null ? job.getJobLocation() : "")).append("\",")
                        .append("\"jobDeadline\":\"").append(escapeJson(job.getJobDeadline() != null ? job.getJobDeadline() : "")).append("\"")
                        .append("}");
                    if (i < jobs.size() - 1) {
                        json.append(",");
                    }
                }
            }
            json.append("],");
            json.append("\"jobsCount\":").append(jobsCount).append(",");
            json.append("\"highestSalaryJob\":");
            if (highestSalaryJob != null) {
                json.append("{")
                    .append("\"jobTitle\":\"").append(escapeJson(highestSalaryJob.getJobTitle())).append("\",")
                    .append("\"jobSalary\":\"").append(escapeJson(highestSalaryJob.getJobSalary())).append("\"")
                    .append("}");
            } else {
                json.append("null");
            }
            json.append(",");
            json.append("\"mostJobsCompany\":");
            if (mostJobsCompany != null) {
                json.append("{")
                    .append("\"companyName\":\"").append(escapeJson(mostJobsCompany.getCompanyName())).append("\",")
                    .append("\"jobCount\":").append(mostJobsCompany.getJobCount())
                    .append("}");
            } else {
                json.append("null");
            }
            json.append("}");

            PrintWriter out = response.getWriter();
            out.print(json.toString());
            out.flush();
        } catch (Exception e) {
            System.err.println("Error in AdminController: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            PrintWriter out = response.getWriter();
            out.print("{\"error\":\"Failed to fetch dashboard data: " + escapeJson(e.getMessage()) + "\"}");
            out.flush();
        }
    }

    // Helper method to escape JSON strings
    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}