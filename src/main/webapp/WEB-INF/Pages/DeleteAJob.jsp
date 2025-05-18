<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/DeleteAJob.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/footer.css">
</head>
<body>

  <div class="layout-container">
<jsp:include page="SideBar.jsp"/>
    <!-- Main Content -->
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
          <h2>Deleta a Job</h2>
          <form action="#" method="post">
            <div class="form-group">
              <label for="deleteJobTitle">Job Title</label>
              <input type="text" id="deleteJobTitle" name="deleteJobTitle" required>
            </div>
            <div class="form-group">
              <label for="deleteCompany">Company Name</label>
              <input type="text" id="deleteCompany" name="deleteCompany" required>
            </div>
            <div class="form-group">
              <button type="submit">Delete</button>
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
        </div>
       
      </div>

    </div>
  </div>
  
<jsp:include page="footer.jsp"/>

</body>
</html>
