<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/AdminDashboard.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/footer.css">
</head>
<body>

<!-- Wrap everything including footer -->
<div class="layout-container">
  
  <!-- Sidebar -->
  <div class="sidebar">
    <div class="logo">Gethiredd</div>
    <ul>
      <li>Dashboard</li>
      <li>Create a Job</li>
      <li>Edit/Delete a Job</li>
      <li>Log Out</li>
    </ul>
  </div>

  <!-- Main Content -->
  <div class="main-content">
    <div class="top-bar">
      <div class="profile">
        <img src="https://via.placeholder.com/40" alt="Admin Profile">
        <span>Admin</span>
      </div>
    </div>

    <div class="greeting">
      <h3>Dashboard Overview!</h3>
    </div>

    <div class="dashboard-cards">
      <div class="card">
        <img src="https://via.placeholder.com/60" alt="Icon">
        <h3>Number of Applications</h3>
        <p>134</p>
      </div>

      <div class="card">
        <img src="https://via.placeholder.com/60" alt="Icon">
        <h3>Jobs Posted</h3>
        <p>27</p>
      </div>

      <div class="card">
        <img src="https://via.placeholder.com/60" alt="Icon">
        <h3>Approved Applications</h3>
        <p>68</p>
      </div>
    </div>
  </div>

</div> <!-- Closing layout-container here is fine -->

<!-- Footer included outside layout but still in body -->
<jsp:include page="footer.jsp"/>

</body>
</html>
