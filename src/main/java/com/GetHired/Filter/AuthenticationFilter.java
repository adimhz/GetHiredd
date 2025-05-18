package com.GetHired.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import com.GetHired.util.CookieUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String HOME = "/home";
    private static final String ROOT = "/";
    private static final String ADMIN = "/admin";
    private static final String AddJob = "/createjobs";
    private static final String EditJob = "/editjob";
    private static final String DeleteJob = "/deletejobs";
    private static final String AddCompany = "/createCompany";
    private static final String DeleteCompany = "/deletecompany";
    private static final String ViewApplication = "/applications";
    private static final String AboutUs = "/aboutus";
    private static final String ContactUs = "/contact";
    private static final String SearhForJobs = "/jobs";
    private static final String SearhForCompany = "/company";
    private static final String BuildYourProfile = "/buildYourProfile";

        private static final Set<String> PUBLIC_PATHS = Set.of(
            "/login", "/register", "/home", "/", "/aboutus", "/contact", "/jobs", "/company"
        );

        private static final Set<String> ADMIN_PATHS = Set.of(
            "/admin", "/createjobs", "/editjob", "/deletejobs",
            "/createCompany", "/deletecompany", "/applications"
        );

        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            String path = req.getRequestURI().substring(req.getContextPath().length());
            HttpSession session = req.getSession(false);
            boolean isLoggedIn = session != null && session.getAttribute("username") != null;

            String role = CookieUtil.getCookie(req, "role") != null
                ? CookieUtil.getCookie(req, "role").getValue()
                : null;

            // Allow static files
            if (path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")) {
                chain.doFilter(request, response);
                return;
            }

            // Allow access to public pages without login
            if (!isLoggedIn) {
                if (PUBLIC_PATHS.contains(path)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect(req.getContextPath() + "/login");
                }
                return;
            }

            // If logged in and trying to access /login or /home, redirect to /jobs
            if (path.equals("/login") || path.equals("/home")) {
                res.sendRedirect(req.getContextPath() + "/jobs");
                return;
            }

            if (ADMIN_PATHS.contains(path) && !"admin".equals(role)) {
                res.sendRedirect(req.getContextPath() + "/unauthorized");
                return;
            }

            // Otherwise, allow access
            chain.doFilter(request, response);
        }
    }
