package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.GetHired.service.DeleteCompanyService;

/**
 * Servlet implementation class DeleteCompany
 */
@WebServlet("/deletecompany")
public class DeleteCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
      DeleteCompanyService deleteCompanyService=new DeleteCompanyService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCompany() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.getRequestDispatcher("/WEB-INF/Pages/DeleteCompany.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        try {
	            String companyName = request.getParameter("CompanyName");
	            String companyContact = request.getParameter("CompanyContact");

	            boolean isDeleted = deleteCompanyService.deleteCompany(companyName, companyContact);

	            if (isDeleted) {
	                request.setAttribute("message", "Company deleted successfully.");
	            } else {
	                request.setAttribute("message", "Failed to delete company. Make sure details are correct.");
	            }

	            request.getRequestDispatcher("/deleteCompany.jsp").forward(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "An error occurred while processing the request.");
	            request.getRequestDispatcher("/deleteCompany.jsp").forward(request, response);
	        }
	    }

}
