<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Job</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CreateAJob.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/footer.css">
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

    <h1 class="heading">Hello Admin!</h1>
    <p class="heading">Running the show, one click at a time!</p>

    <div class="card-wrapper">
      <div class="form-container">
        <h2>Edit a Job</h2>
        
        <!-- Form to Fetch Job Details -->
        <form action="editjob" method="get">
          <div class="form-group">
            <label for="jobTitle">Job Title</label>
            <input type="text" id="jobTitle" name="jobTitle" required value="${param.jobTitle}">
          </div>
          <div class="form-group">
            <label for="companyName">Company Name</label>
            <select id="companyName" name="companyName" required>
              <option value="">Select a company</option>
              <c:forEach var="company" items="${companies}">
                <option value="${company.companyName}" ${company.companyName == selectedCompanyName ? 'selected' : ''}>
                  ${company.companyName}
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <button type="submit">Fetch Job Details</button>
          </div>
        </form>

        <!-- Display Message -->
        <c:if test="${not empty message}">
          <p class="message">${message}</p>
        </c:if>

        <!-- Form to Update Job Details -->
        <c:if test="${not empty job}">
          <form action="editjob" method="post">
            <div class="form-group">
              <label for="jobTitle">Job Title</label>
              <input type="text" id="jobTitle" name="jobTitle" required value="${job.jobTitle}">
            </div>
            <div class="form-group">
              <label for="companyName">Company Name</label>
              <select id="companyName" name="companyName" required>
                <option value="">Select a company</option>
                <c:forEach var="company" items="${companies}">
                  <option value="${company.companyName}" ${company.companyName == selectedCompanyName ? 'selected' : ''}>
                    ${company.companyName}
                  </option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group">
              <label for="jobType">Job Type</label>
              <input type="text" id="jobType" name="jobType" value="${job.jobType}">
            </div>
            <div class="form-group">
              <label for="jobDeadline">Deadline</label>
              <input type="date" id="jobDeadline" name="jobDeadline" value="${job.jobDeadline}">
            </div>
            <div class="form-group">
              <label for="jobQualification">Qualification</label>
              <input type="text" id="jobQualification" name="jobQualification" value="${job.jobQualification}">
            </div>
            <div class="form-group">
              <label for="jobSalary">Salary</label>
              <input type="text" id="jobSalary" name="jobSalary" value="${job.jobSalary}">
            </div>
            <div class="form-group">
              <label for="jobLocation">Location</label>
              <input type="text" id="jobLocation" name="jobLocation" value="${job.jobLocation}">
            </div>
            <div class="form-group">
              <label for="jobDescription">Job Description</label>
              <textarea id="jobDescription" name="jobDescription" rows="5">${job.jobDescription}</textarea>
            </div>
            <div class="form-group">
              <button type="submit">Update Job</button>
            </div>
          </form>
            <% String error = (String) request.getAttribute("error");
               if (error != null) { %>
               <p style="color: red;"><%= error %></p>
            <% } %>
            <% String success = (String) request.getAttribute("success");
               if (success != null) { %>
               <p style="color: green;"><%= success %></p>
            <% } %>
        </c:if>
      </div>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>