package com.GetHired.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
System.out.print("Logged out");
        // Invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Optional: clear role cookie
        Cookie roleCookie = new Cookie("role", "");
        roleCookie.setMaxAge(0); // delete it
        roleCookie.setPath("/"); // ensure correct path
        response.addCookie(roleCookie);

        // Redirect to login page
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
