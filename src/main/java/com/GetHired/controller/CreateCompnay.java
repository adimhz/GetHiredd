package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.GetHired.model.Company;
import com.GetHired.service.CreateCompanyService;

/**
 * Servlet implementation class CreateCompnay
 */
@MultipartConfig
@WebServlet(name = "CreateCompany", urlPatterns = { "/createCompany" })
public class CreateCompnay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCompnay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/Pages/CreateACompany.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            Company company = extractCompanyModel(request);
	            CreateCompanyService service = new CreateCompanyService();
	            Boolean isCreated = service.createcompany(company);

	            if (isCreated == null) {
	                handleError(request, response, "Our server is under maintenance");
	            } else if (isCreated) {
	                handleSuccess(request, response, "Company added successfully!", "/WEB-INF/Pages/CreateACompany.jsp");
	            } else {
	                handleError(request, response, "Could not add job. Please try again later!");
	            }
	        } catch (Exception e) {
	            // Optional: log the exception
	            e.printStackTrace();
	            handleError(request, response, "An unexpected error occurred. Please try again later!");
	        }
	    }

	    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
	            throws ServletException, IOException {

	        req.setAttribute("error", message);
	        req.getRequestDispatcher("/WEB-INF/Pages/CreateACompany.jsp").forward(req, resp);
	    }

	    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
	            throws ServletException, IOException {
	        req.setAttribute("success", message);
	        req.getRequestDispatcher("/WEB-INF/Pages/CreateACompany.jsp").forward(req, resp);
	    }

	    private Company extractCompanyModel(HttpServletRequest req) {
	        String CompanyName = req.getParameter("CompanyName");
	        String CompanyLocation = req.getParameter("CompanyLocation");
	        String CompanyContact = req.getParameter("CompanyContact");


	        return new Company(CompanyName,CompanyLocation,CompanyContact);
	    }
	}


