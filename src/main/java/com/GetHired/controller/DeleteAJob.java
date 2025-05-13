package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.GetHired.service.DeleteJobService;

/**
 * Servlet implementation class DeleteAJob
 */
@WebServlet("/deletejobs")
public class DeleteAJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAJob() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/Pages/DeleteAJob.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	        String jobTitle = request.getParameter("deleteJobTitle");
	        String companyName = request.getParameter("deleteCompany");

	        DeleteJobService service = new DeleteJobService();
	        boolean isDeleted = service.deleteJob(jobTitle, companyName);

	        if (isDeleted) {
	            request.setAttribute("success", "Job deleted successfully.");
	        } else {
	            request.setAttribute("error", "Job not found or could not be deleted.");
	        }

	        // Redirect or forward accordingly
	        request.getRequestDispatcher("/WEB-INF/Pages/DeleteJobResult.jsp").forward(request, response);
	    }

}
