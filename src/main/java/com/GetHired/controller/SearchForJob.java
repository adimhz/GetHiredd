package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.GetHired.model.jobModel;
import com.GetHired.service.ShowJobs;

@WebServlet("/jobs")
@MultipartConfig
public class SearchForJob extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchForJob() {
        super();
    }

    // doGet: This method loads the full job list when the page is first accessed.
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.print("doGet called");

        ShowJobs service = new ShowJobs();
        List<jobModel> jobs = service.getAllJobs();

        request.setAttribute("jobList", jobs);
        request.getRequestDispatcher("/WEB-INF/Pages/SearchForJob.jsp").forward(request, response);
    }

    // doPost: This method handles the filtering and sorting when the user submits the form.
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get search and sort parameters from the form
        String searchQuery = request.getParameter("searchQuery");
        String sortBy = request.getParameter("sortBy");

        // If there are no parameters, return all jobs (no filtering or sorting)
        if (searchQuery == null && sortBy == null) {
            doGet(request, response);  // Reuse the doGet method to load all jobs
            return;
        }

        // Call the service to get filtered jobs based on search query and sort option
        ShowJobs service = new ShowJobs();
        List<jobModel> jobs = service.getFilteredJobs(searchQuery, sortBy);

        // Forward the filtered list to the JSP
        request.setAttribute("jobList", jobs);
        request.getRequestDispatcher("/WEB-INF/Pages/SearchForJob.jsp").forward(request, response);
    }
}
