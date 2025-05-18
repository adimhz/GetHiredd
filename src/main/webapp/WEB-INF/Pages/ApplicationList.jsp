<%@ page import="java.util.List" %>
<%@ page import="com.GetHired.model.ApplicationModel" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>All Applications</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/footer.css">
<style>
.main-content {
  flex-grow: 1;
  background:linear-gradient(to right, #e2b39d, #cdb7ad);
  color: white; /* White text for content */
}
.layout-container {
  display: flex;
  min-height: 100vh;
  background-color: #e2b39d;
}
  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin: 0;
    padding: 20px;
    background-color: #f4f6f8;
    color: #333;
  }
  

  h1 {
    text-align: center;
    color: #2c3e50;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 30px;
    background-color: black;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    overflow: hidden;
  }

  th, td {
    padding: 14px 20px;
    border-bottom: 1px solid #ddd;
    text-align: left;
  }

  th {
    background-color: #2980b9;
    color: white;
    font-weight: 600;
  }

  tr:hover {
    background-color: black;
  }

  p {
    text-align: center;
    margin-top: 40px;
    font-size: 18px;
    color: #666;
  }
</style>

</head>
<body>
<div class=layout-container>
 <jsp:include page="SideBar.jsp"/>
 <div class=main-content>
  <h1>Submitted Applications</h1>

  <% 
     // pull the list out of request
     List<ApplicationModel> apps = (List<ApplicationModel>) request.getAttribute("applications");
     if (apps == null || apps.isEmpty()) {
  %>
      <p>No applications found.</p>
  <%
     } else {
  %>
      <table>
        <thead>
          <tr>
            <th>Job Title</th>
            <th>Location</th>
            <th>Applicant Name</th>
            <th>Applicant Email</th>
          </tr>
        </thead>
        <tbody>
    <%
        for (ApplicationModel app : apps) {
    %>
          <tr>
            <td><%= app.getJobTitle()    %></td>
            <td><%= app.getJobLocation() %></td>
            <td><%= app.getUserName()    %></td>
            <td><%= app.getUserEmail()   %></td>
          </tr>
    <%
        }
    %>
        </tbody>
      </table>
  <%
     }
  %>
  </div>
  </div>
   <jsp:include page="footer.jsp"/>
   
</body>
</html>
