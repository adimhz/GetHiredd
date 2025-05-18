<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Jobs - Get Hiredd</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', sans-serif;
      text-decoration: none;
    }
    body {
      background: linear-gradient(to right, #e2b39d, #cdb7ad);
      min-height: 100vh;
      display: flex;
      flex-direction: column;
    }
    .navbar {
      background: #000;
      padding: 20px 40px;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
    }
    .navdiv {
      display: flex;
      align-items: center;
      justify-content: space-between;
      flex-wrap: wrap;
    }
    .logo a {
      font-size: 28px;
      font-weight: bold;
      color: white;
    }
    ul {
      display: flex;
      align-items: center;
      gap: 20px;
      flex-wrap: wrap;
    }
    ul li {
      list-style: none;
    }
    ul li a {
      color: #f9f9f9;
      font-size: 16px;
      font-weight: 600;
      transition: color 0.3s ease;
    }
    ul li a:hover {
      color: #ff6a6a;
    }
    .buttons {
      display: flex;
      gap: 12px;
      margin-top: 10px;
    }
    .button {
      background-color: #ff6a6a;
      border: 1px solid #ccc;
      padding: 8px 16px;
      border-radius: 8px;
      transition: background-color 0.3s ease;
    }
    .button a {
      color: #333;
      font-weight: bold;
      font-size: 14px;
    }
    .button:hover {
      background-color: #f0f0f0;
    }
    main {
      padding: 50px 20px;
      flex: 1;
    }
    .job-title {
      text-align: center;
      font-size: 36px;
      margin-bottom: 40px;
      color: white;
    }
    .jobs-grid {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
      justify-content: center;
    }
    .job-card {
      background: #444;
      border: 1px solid #ddd;
      border-radius: 12px;
      flex: 1 1 calc(50% - 20px);
      max-width: 450px;
      padding: 20px;
      margin-bottom: 20px;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
      transition: transform 0.2s ease;
    }
    .job-card:hover {
      transform: translateY(-5px);
    }
    .job-details h3 {
      font-size: 20px;
      color: white;
      margin-bottom: 5px;
    }
    .job-details p {
      font-size: 14px;
      color: white;
      margin-bottom: 2px;
    }
    .apply-section {
      text-align: right;
      margin-top: 10px;
    }
    .apply-button {
      background-color: #ff6a6a;
      color: white;
      padding: 8px 16px;
      border: none;
      border-radius: 6px;
      font-size: 14px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    .apply-button:hover:not(:disabled) {
      background-color: #d93636;
    }
    .apply-button:disabled {
      background-color: #555;
      cursor: not-allowed;
    }
    .footer-container {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      padding: 40px;
      background-color: #111;
      color: #fff;
    }
    .footer-left {
      display: flex;
      width: 48%;
      gap: 70px;
      padding-top: 80px;
      padding-left: 50px;
    }
    .footer-logo-tagline {
      display: flex;
      flex-direction: column;
      gap: 20px;
    }
    .footer-links {
      display: flex;
      flex-direction: column;
      gap: 10px;
    }
    .footer-links a {
      color: #ccc;
      text-decoration: none;
      font-size: 14px;
    }
    .footer-links a:hover {
      color: #fff;
    }
    .footer-right {
      width: 48%;
    }
    .footer-right iframe {
      width: 100%;
      height: 250px;
      border-radius: 10px;
    }
    .footer-bottom {
      text-align: center;
      padding: 20px;
      background-color: #000;
      color: #777;
      font-size: 14px;
    }
    .search-sort-container {
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 20px;
      margin-bottom: 30px;
      flex-wrap: wrap;
    }
    .search-bar {
      padding: 10px 20px;
      width: 600px;
      max-width: 90%;
      border: 1px solid #a15b5b;
      border-radius: 8px;
      font-size: 16px;
      background: #e6dfdf;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
      transition: border-color 0.3s ease;
    }
    .search-bar:focus {
      border-color: #ff6a6a;
      outline: none;
    }
    .sort-bar {
      padding: 10px 20px;
      width: 200px;
      max-width: 90%;
      border: 1px solid #dfdddd;
      border-radius: 8px;
      font-size: 16px;
      background: #444;
      color: white;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
      transition: border-color 0.3s ease;
    }
    .sort-bar:focus {
      border-color: #ff6a6a;
      outline: none;
    }
    .search-button {
      background-color: #ff6a6a;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 8px;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    .search-button:hover {
      background-color: #d93636;
    }
    .nav-profile {
      display: flex;
      align-items: center;
      gap: 15px;
    }
    .profile-pic {
      width: 40px;
      height: 40px;
      border-radius: 50%;
      object-fit: cover;
      border: 2px solid #fff;
    }
    .logout-button {
      background-color: #ff4d4d;
      color: white;
      padding: 8px 16px;
      border: none;
      border-radius: 6px;
      font-size: 14px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    .logout-button:hover {
      background-color: #d93636;
    }
    .message {
      background-color: #ff6a6a;
      color: white;
      padding: 10px;
      border-radius: 5px;
      margin-bottom: 20px;
      text-align: center;
    }
    @media (max-width: 768px) {
      .navdiv, .footer-container, .job-card {
        flex-direction: column;
        align-items: center;
        text-align: center;
      }
      ul {
        flex-direction: column;
        margin-top: 10px;
      }
      .buttons {
        flex-direction: column;
        width: 100%;
      }
      .job-card {
        flex: 1 1 100%;
      }
      .apply-section {
        margin-top: 10px;
      }
      .search-sort-container {
        flex-direction: column;
      }
      .search-bar, .sort-bar, .search-button {
        width: 100%;
      }
    }
  </style>
</head>

<jsp:include page="header.jsp"/>

<main>

  <h1 class="job-title">Jobs</h1>
  <div class="search-sort-container">
    <form action="jobs" method="post">
      <input type="text" class="search-bar" name="searchQuery" value="${param.searchQuery}" placeholder="Search for jobs...">
      <button type="submit" class="search-button">Search</button>
    </form>
  </div>
<div class="jobs-grid">
  <c:choose>
    <c:when test="${not empty jobList}">
      <c:forEach var="job" items="${jobList}">
        <div class="job-card">
          <div class="job-details">
            <h3><c:out value="${job.jobTitle}"/></h3>
            <p><strong>Company:</strong> <c:out value="${job.jobCompanyId.companyName}"/></p>
            <p><strong>Location:</strong> <c:out value="${job.jobLocation}"/></p>
            <p><strong>Qualification:</strong> <c:out value="${job.jobQualification}"/></p>
            <p><strong>Job Description:</strong> <c:out value="${job.jobDescription}"/></p>
            <p><strong>Deadline:</strong> <c:out value="${job.jobDeadline}"/></p>
            <p><strong>Salary:</strong> <c:out value="${job.jobSalary}"/></p>
          </div>
          <div class="apply-section">
            <form action="${pageContext.request.contextPath}/applyJob" method="post">
              <input type="hidden" name="jobId" value="${job.jobId}"/>
              <button type="submit" class="apply-button"
                <c:if test="${job.hasApplied}">disabled</c:if>>
                <c:choose>
                  <c:when test="${job.hasApplied}">
                    Already Applied
                  </c:when>
                  <c:otherwise>
                    Apply
                  </c:otherwise>
                </c:choose>
              </button>
              <!-- Display message only for the specific job -->
              <c:if test="${not empty message and appliedJobId == job.jobId}">
                <div class="message"><c:out value="${message}"/></div>
              </c:if>
            </form>
          </div>
        </div>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <p style="color: white; text-align: center;">No jobs found.</p>
    </c:otherwise>
  </c:choose>
</div>
</main>

<jsp:include page="footer.jsp"/>

</body>
</html>