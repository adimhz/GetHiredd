<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Job</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/EditAJob.css">
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
        <form action="editjob" method="post">
          <!-- Job Title to Search / Update -->
          <div class="form-group">
            <label for="jobTitle">Job Title</label>
            <input type="text" id="jobTitle" name="jobTitle" required value="${job.jobTitle}">
          </div>

          <div class="form-group">
            <label for="jobType">Job Type</label>
            <input type="text" id="jobType" name="jobType" required value="${job.jobType}">
          </div>

          <div class="form-group">
            <label for="jobDeadline">Deadline</label>
            <input type="text" id="jobDeadline" name="jobDeadline" required value="${job.jobDeadline}">
          </div>

          <div class="form-group">
            <label for="jobQualification">Qualification</label>
            <input type="text" id="jobQualification" name="jobQualification" required value="${job.jobQualification}">
          </div>

          <div class="form-group">
            <label for="jobSalary">Salary</label>
            <input type="text" id="jobSalary" name="jobSalary" required value="${job.jobSalary}">
          </div>

          <div class="form-group">
            <label for="jobLocation">Location</label>
            <input type="text" id="jobLocation" name="jobLocation" required value="${job.jobLocation}">
          </div>

          <div class="form-group">
            <label for="jobDescription">Job Description</label>
            <textarea id="jobDescription" name="jobDescription" rows="5" required>${job.jobDescription}</textarea>
          </div>

          <div class="form-group">
            <button type="submit">Update Job</button>
          </div>
        </form>

  
      </div>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
