<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Jobs - Get Hiredd</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/SearchForJob.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/footer.css">
</head>
<style>
@charset "UTF-8";
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-family: 'Segoe UI', Arial, sans-serif;
  text-decoration: none;
}

body {
  background: linear-gradient(to right, #cdb7ad, #ffffff);
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

/* Main Section */
main {
  padding: 50px 20px;
  flex: 1;
}

/* Job Title */
.job-title {
  text-align: center;
  font-size: 36px;
  margin-bottom: 40px;
  color: #333;
}

/* Search and Sort Container */
.search-sort-container {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  margin-bottom: 40px;
  flex-wrap: wrap;
}

.search-bar {
  padding: 12px 20px;
  width: 600px;
  max-width: 90%;
  border: 2px solid #ff6a6a;
  border-radius: 8px;
  font-size: 16px;
  background: #ffffff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

.search-bar:focus {
  border-color: #1976d2;
  box-shadow: 0 4px 16px rgba(25, 118, 210, 0.2);
  outline: none;
}

.sort-bar {
  padding: 12px 20px;
  width: 200px;
  max-width: 90%;
  border: 2px solid #ff6a6a;
  border-radius: 8px;
  font-size: 16px;
  background: #ffffff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: border-color 0.3s ease;
}

.sort-bar:focus {
  border-color: #1976d2;
  outline: none;
}

.search-button {
  padding: 12px 20px;
  width: 120px;
  max-width: 90%;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  background: #ff6a6a;
  color: white;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.search-button:hover {
  background: #e55a5a;
}

/* Jobs Grid */
.jobs-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
  max-width: 1000px;
  margin: 0 auto;
}

/* Job Card */
.job-card {
  background: #ffffff;
  border: 2px solid #ff6a6a;
  border-radius: 12px;
  padding: 15px;
  flex: 1 1 calc(50% - 20px); /* Flexible for 2 cards side by side */
  max-width: 600px; /* Increased to match screenshot width */
  min-width: 300px; /* Minimum width for flexibility */
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.12);
  transition: transform 0.2s ease, border-color 0.3s ease;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.job-card:hover {
  transform: translateY(-5px);
  border-color: #1976d2;
}

/* Job Details */
.job-details {
  flex: 1;
  margin: 0; /* Remove vertical gaps */
}

.job-details h3 {
  font-size: 20px;
  color: #333;
  margin: 0 0 4px 0; /* Tighten spacing between lines */
}

.job-details p {
  font-size: 14px;
  color: #666;
  margin: 0 0 2px 0; /* Minimize vertical gaps */
}

/* Apply Section */
.apply-section {
  text-align: right;
  margin: 0; /* Remove extra margins */
  padding: 0;
}

.apply-button {
  background-color: #1976d2;
  color: white;
  padding: 12px 24px; /* Bigger button */
  border: none;
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  display: inline-block;
  width: auto;
}

.apply-button:hover {
  background-color: #155a9c;
}

/* Empty State */
.jobs-grid p {
  text-align: center;
  font-size: 16px;
  color: #666;
  padding: 20px;
  width: 100%;
}

/* Responsive Design */
@media (max-width: 768px) {
  .search-sort-container {
    flex-direction: column;
  }

  .search-bar,
  .sort-bar,
  .search-button {
    width: 100%;
  }

  .job-card {
    flex: 1 1 100%;
    max-width: 90%;
    min-width: 0;
    flex-direction: column;
    align-items: stretch;
  }

  .apply-section {
    text-align: left;
    margin-top: 10px;
  }
}</style>
<body>
  <jsp:include page="header.jsp"/>
  <main>
    <h1 class="job-title">Jobs</h1>
    <!-- Form for Search and Sort -->
    <form action="${pageContext.request.contextPath}/jobs" method="POST">
      <div class="search-sort-container">
        <input type="text" name="searchQuery" class="search-bar" placeholder="Search for jobs...">
        <select name="sortBy" class="sort-bar">
          <option value="">Sort By</option>
          <option value="latest">Latest</option>
          <option value="salary-high-low">Salary: High to Low</option>
          <option value="salary-low-high">Salary: Low to High</option>
        </select>
        <button type="submit" class="search-button">Search</button>
      </div>
    </form>
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
                <a href="ApplyForAJob.jsp?jobTitle=${job.jobTitle}" class="apply-button">Apply</a>
              </div>
            </div>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <p>No jobs available at the moment.</p>
        </c:otherwise>
      </c:choose>
    </div>
  </main>
  <jsp:include page="footer.jsp"/>
</body>
</html>