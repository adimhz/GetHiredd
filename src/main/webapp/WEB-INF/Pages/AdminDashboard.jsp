<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/AdminDashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Sidebar.css">
</head>
<body>
<div class="layout-container">
    <jsp:include page="SideBar.jsp"/>
    <div class="main-content">
        <div class="top-bar">
            <div class="profile">
                <img
                    src="${pageContext.request.contextPath}/resources/images/userProfileImages/${empty sessionScope.profilePicture ? 'default.jpg' : sessionScope.profilePicture}"
                    alt="Profile"
                    class="profile-img"
                    onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/images/userProfileImages/default.jpg';"
                />
                <span>Admin</span>
            </div>
        </div>
        <div class="dashboard-cards">
            <div class="card">
                <h3>Total Jobs Posted: <span><c:out value="${jobsCount}" default="0" /></span></h3>
            </div>
            <div class="card">
                <h3>Highest Salary Job: 
                    <span>
                        <c:choose>
                            <c:when test="${not empty highestSalaryJob}">
                                <c:out value="${highestSalaryJob.jobTitle} (${highestSalaryJob.jobSalary})" />
                            </c:when>
                            <c:otherwise>No jobs available</c:otherwise>
                        </c:choose>
                    </span>
                </h3>
            </div>
            <div class="card">
                <h3>Top Company: 
                    <span>
                        <c:choose>
                            <c:when test="${not empty mostJobsCompany}">
                                <c:out value="${mostJobsCompany.companyName} (${mostJobsCompany.jobCount} jobs)" />
                            </c:when>
                            <c:otherwise>No companies available</c:otherwise>
                        </c:choose>
                    </span>
                </h3>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>