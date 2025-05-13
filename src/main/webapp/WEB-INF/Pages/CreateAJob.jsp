<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creating job</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CreateAJob.css">
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
<h1 class="heading">Hello Admin!</h1>
<p class="heading">Running the show, one click at a time!</p>
      <!-- Centered Form Cards -->
      <div class="card-wrapper">
        <!-- Create Job Form -->
          <div class="form-container">
          <h2>Create a Job</h2>
        <form action="${pageContext.request.contextPath}/createjobs" method="post" enctype="multipart/form-data">
          
            <div class="form-group">
              <label for="CreateJobTitle">Job Title</label>
              <input type="text" id="CreateJobTitle" name="CreateJobTitle" required>
            </div>
            <div class="form-group">
              <label for="JobType">Job Type</label>
              <input type="text" id="JobType" name="JobType" required>
            </div>
            <div class="form-group">
              <label for="Deadline">Deadline</label>
              <input type="date" id="Deadline" name="Deadline" required>
            </div>
            <div class="form-group">
              <label for="Qualification">Qualification</label>
              <input type="text" id="Qualification" name="Qualification" required>
            </div>
            <div class="form-group">
              <label for="company">Company</label>
              <input type="text" id="company" name="Company" required>
            </div>
            <div class="form-group">
              <label for="salary">Salary</label>
              <input type="text" id="salary" name="Salary"  required>
            </div>
            <div class="form-group">
              <label for="location">Location</label>
              <input type="text" id="location" name="Location"  required>
            </div>
            <div class="form-group">
              <label for="description">Job Description</label>
              <textarea id="description" name="description" rows="5" required></textarea>
            </div>
            <div class="form-group">
              <button type="submit">Create</button>
            </div>
          </form>
        </div>
      </div>

    </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>