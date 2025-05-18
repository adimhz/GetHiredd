<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Sidebar.css">
 <style>
/* Sidebar */
.sidebar {
  width: 240px;
  background-color: #222; /* Dark background for sidebar */
  color: white;
  padding: 20px 0;
  border-radius: 15px;
  margin: 20px;
  height: calc(100vh - 40px); /* Adjusted to 100vh for better fit */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* Subtle shadow for depth */
}

.logo {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 30px;
  letter-spacing: 1px;
  color: #fff; /* Ensure logo text is white */
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0; /* Remove default margins */
}

.sidebar ul li {
  margin: 10px 15px; /* Spacing between buttons */
  border-radius: 8px; /* Rounded corners for button-like appearance */
  overflow: hidden; /* Ensure child elements respect border-radius */
  transition: transform 0.3s, box-shadow 0.3s; /* Smooth transitions */
}

.sidebar ul li:hover {
  transform: scale(1.03); /* Slightly smaller scale for subtlety */
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.4); /* Subtle shadow on hover */
}

.sidebar ul li a {
  display: block; /* Make the link fill the entire <li> */
  padding: 12px 20px; /* Comfortable padding */
  text-decoration: none; /* Remove underline from links */
  color: #fff; /* White text */
  background-color: #333; /* Slightly lighter background for buttons */
  border-radius: 8px; /* Match <li> border-radius */
  font-size: 16px; /* Readable font size */
  font-weight: 500; /* Medium weight for better readability */
  text-align: center; /* Center the text */
  transition: background-color 0.3s, color 0.3s; /* Smooth color transitions */
}

.sidebar ul li a:hover {
  background-color: #2E7D32; /* Green background on hover */
  color: #fff; /* White text on hover */
  text-shadow: none; /* Remove text-shadow for cleaner look */
}</style>
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
      <li><a href="applications">Application List</a></li>
      <li><a href="logout">Log Out</a></li>
      </ul>
    </div>
    </div>
</body>
</html>