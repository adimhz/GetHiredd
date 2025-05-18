// com/GetHired/controller/ApplicationListController.java
package com.GetHired.controller;

import com.GetHired.model.ApplicationModel;
import com.GetHired.service.DisplayApplications;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/applications")
public class DisplayApplicationController extends HttpServlet {
    private final DisplayApplications viewSvc = new DisplayApplications();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ApplicationModel> apps = viewSvc.getAllApplications();
        req.setAttribute("applications", apps);
        req.getRequestDispatcher("/WEB-INF/Pages/ApplicationList.jsp").forward(req, resp);
    }
}
