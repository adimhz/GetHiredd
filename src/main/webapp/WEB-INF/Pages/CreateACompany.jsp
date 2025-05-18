<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creating job</title>
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
      <!-- Centered Form Cards -->
      <div class="card-wrapper">
        <!-- Create Company Form -->
          <div class="form-container">
          <h2>Create a Company</h2>
          <form action="${pageContext.request.contextPath}/createCompany" method="post" enctype="multipart/form-data">
            <div class="form-group">
              <label for="CompanyName">Company Name</label>
              <input type="text" id="CompanyName" name="CompanyName" required>
            </div>
            <div class="form-group">
              <label for="CompanyLocation">Company Location</label>
              <input type="text" id="CompanyLocation" name="CompanyLocation" required>
            </div>
            <div class="form-group">
              <label for="CompanyContact">Company Contact</label>
              <input type="text" id="CompanyContact" name="CompanyContact" required>
            </div>
            <div class="form-group">
              <button type="submit">Create</button>
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