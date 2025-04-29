<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Jobs - Get Hiredd</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/SearchForJob.css">
</head>
<body>

<jsp:include page="header.jsp"/>
  <main>
    <h1 class="job-title">Jobs</h1>
    <div class="search-sort-container">
      <input type="text" class="search-bar" placeholder="Search for jobs...">
      <select class="sort-bar">
        <option value="">Sort By</option>
        <option value="latest">Latest</option>
        <option value="salary-high-low">Salary: High to Low</option>
        <option value="salary-low-high">Salary: Low to High</option>
      </select>
    </div>
    <div class="jobs-grid">
    <div class="job-card">
      <div class="job-info">
        <img src="${pageContext.request.contextPath}/resources/images/Company4.png" alt="Company Logo">
        <div class="job-details">
          <h3>Office Assistant</h3>
          <p>We are working Pvt.Ltd</p>
          <p>Lalitpur, Nepal</p>
        </div>
      </div>
      
      <div class="apply-section">
        <div class="salary">Salary: 50K-60K</div>
        <a href="ApplyForAJob.html" class="apply-button">Apply</a>

      </div>
    </div>
    <div class="job-card">
      <div class="job-info">
        <img src="${pageContext.request.contextPath}/resources/images/Company3.png" alt="Company Logo">
        <div class="job-details">
          <h3>Office Assistant</h3>
          <p>We are working Pvt.Ltd</p>
          <p>Lalitpur, Nepal</p>
        </div>
      </div>
      
      <div class="apply-section">
        <div class="salary">Salary: 50K-60K</div>
        <a href="ApplyForAJob.html" class="apply-button">Apply</a>

      </div>
    </div>

    <div class="job-card">
      <div class="job-info">
        <img src="${pageContext.request.contextPath}/resources/images/Company5.png" alt="Company Logo">
        <div class="job-details">
          <h3>Office Assistant</h3>
          <p>We are working Pvt.Ltd</p>
          <p>Lalitpur, Nepal</p>
        </div>
      </div>
      <div class="apply-section">
        <div class="salary">Salary: 50K-60K</div>
        <a href="ApplyForAJob.html" class="apply-button">Apply</a>

      </div>
    </div>

    <div class="job-card">
      <div class="job-info">
        <img src="${pageContext.request.contextPath}/resources/images/Company2.png" alt="Company Logo">
        <div class="job-details">
          <h3>Office Assistant</h3>
          <p>We are working Pvt.Ltd</p>
          <p>Lalitpur, Nepal</p>
        </div>
      </div>
      <div class="apply-section">
        <div class="salary">Salary: 50K-60K</div>
        <a href="ApplyForAJob.html" class="apply-button">Apply</a>

      </div>
    </div>
    <div class="job-card">
      <div class="job-info">
        <img src="${pageContext.request.contextPath}/resources/images/Company4.png" alt="Company Logo">
        <div class="job-details">
          <h3>Office Assistant</h3>
          <p>We are working Pvt.Ltd</p>
          <p>Lalitpur, Nepal</p>
        </div>
      </div>
      <div class="apply-section">
        <div class="salary">Salary: 50K-60K</div>
        <a href="ApplyForAJob.html" class="apply-button">Apply</a>

      </div>
    </div>
    <div class="job-card">
      <div class="job-info">
        <img src="${pageContext.request.contextPath}/resources/images/Company2.png" alt="Company Logo">
        <div class="job-details">
          <h3>Office Assistant</h3>
          <p>We are working Pvt.Ltd</p>
          <p>Lalitpur, Nepal</p>
        </div>
      </div>
      <div class="apply-section">
        <div class="salary">Salary: 50K-60K</div>
        <a href="ApplyForAJob.html" class="apply-button">Apply</a>

      </div>
    </div>
    </div>

  </main>
<jsp:include page="footer.jsp"/>

</body>
</html>
