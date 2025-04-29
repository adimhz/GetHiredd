package com.GetHired.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.GetHired.util.CookieUtil;
import com.GetHired.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String HOME = "/home";
    private static final String ROOT = "/";
    private static final String ADMIN = "/admin";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = session != null && session.getAttribute("username") != null;
        String userRole = CookieUtil.getCookie(req, "role") != null
                ? CookieUtil.getCookie(req, "role").getValue()
                : null;


        // Allow static resources
        if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".css") || uri.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }

        // Public pages (accessible to anyone)
        boolean isPublicPage = uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME) || uri.equals(ROOT);

        if (!isLoggedIn) {
            // Not logged in
            if (isPublicPage) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + LOGIN);
            }
        } else {
            // Logged in
            if (uri.endsWith(LOGIN) || uri.endsWith(HOME)) {
                res.sendRedirect(req.getContextPath() + "/jobs");
            } else if (uri.endsWith(ADMIN) && !"admin".equals(userRole)) {
                res.sendRedirect(req.getContextPath() + "/jobs");
            } else {
                // Allow /register and everything else
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        // No cleanup needed
    }
}
