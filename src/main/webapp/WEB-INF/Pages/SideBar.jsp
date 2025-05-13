<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <style>
@charset "UTF-8";
/* Sidebar */
.sidebar {
  width: 240px;
  background-color: #222; /* Dark background for sidebar/nav */
  color: white;
  padding: 20px 0;
  border-radius: 15px;
  margin: 20px;
  height: calc(140vh - 40px);
}

.logo {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 30px;
  letter-spacing: 1px;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  height:fit-content;
}

.sidebar ul li {
  padding: 12px 25px;
  cursor: pointer;
  transition: background 0.3s;
}

.sidebar ul li:hover {
  background-color:black;
  color: #2E7D32;
  transform: scale(1.05);
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2); /* Slightly lighter hover effect */
}

</style>
</head>

<body>
 <div class="layout-container">
    <!-- Sidebar -->
    <div class="sidebar">
      <div class="logo">Gethiredd</div>
      <ul>
      <li><a href="admin">Dashboard</a></li>
      <li><a href="createjobs">Create a Job</a></li>
      <li><a href="editjob">Edit a Job</a></li>
      <li><a href="deletejobs">Delete a Job</a></li>
      <li><a href="createCompany">Create a Company</a></li>
      <li><a href="deletecompany">Delete a Company</a></li>
      <li><a href="home">Log Out</a></li>
      </ul>
    </div>
    </div>
</body>
</html>