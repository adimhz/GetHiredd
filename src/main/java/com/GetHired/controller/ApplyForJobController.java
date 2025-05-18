package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.GetHired.service.ApplicationService;

import java.io.IOException;

@WebServlet("/applyJob")
public class ApplyForJobController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ApplicationService applicationService = new ApplicationService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String userId = (String) session.getAttribute("username");
        if (userId == null) {
            session.setAttribute("message", "Please log in to apply.");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String jobIdStr = request.getParameter("jobId");
        if (jobIdStr == null || jobIdStr.trim().isEmpty()) {
            session.setAttribute("message", "Job ID is missing.");
            response.sendRedirect(request.getContextPath() + "/jobs");
            return;
        }

        int jobId;
        try {
            jobId = Integer.parseInt(jobIdStr);
        } catch (NumberFormatException e) {
            session.setAttribute("message", "Invalid job ID.");
            response.sendRedirect(request.getContextPath() + "/jobs");
            return;
        }

        boolean success = applicationService.applyForJob(userId, jobId);
        if (success) {
            session.setAttribute("message", "Applied successfully.");
        } else {
            session.setAttribute("message", "Already applied or error occurred.");
        }
        response.sendRedirect(request.getContextPath() + "/jobs");
    }
}
