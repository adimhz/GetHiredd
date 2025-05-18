package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.GetHired.model.jobModel;
import com.GetHired.service.ApplicationService;
import com.GetHired.service.ShowJobs;

import java.io.IOException;
import java.util.List;

@WebServlet("/jobs")
@MultipartConfig
public class SearchForJob extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ShowJobs showJobsService = new ShowJobs();
    private final ApplicationService appService = new ApplicationService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<jobModel> jobs = showJobsService.getAllJobs();
        markAppliedFlags(request, jobs);
        request.setAttribute("jobList", jobs);
        request.getRequestDispatcher("/WEB-INF/Pages/SearchForJob.jsp")
               .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");

        List<jobModel> jobs;
        if ((searchQuery == null || searchQuery.isEmpty())) {
            jobs = showJobsService.getAllJobs();
        } else {
            jobs = showJobsService.getFilteredJobs(searchQuery);
        }

        markAppliedFlags(request, jobs);
        request.setAttribute("jobList", jobs);
        request.getRequestDispatcher("/WEB-INF/Pages/SearchForJob.jsp")
               .forward(request, response);
    }

    /**
     * For each job in the list, check if the current user has applied,
     * and set job.hasApplied accordingly.
     */
    private void markAppliedFlags(HttpServletRequest request, List<jobModel> jobs) {
        HttpSession session = request.getSession(false);
        String userId = (session != null) ? (String) session.getAttribute("username") : null;

        if (userId != null) {
            for (jobModel job : jobs) {
                boolean applied = appService.hasApplied(userId, job.getJobId());
                job.setHasApplied(applied);
            }
        }
    }
}
