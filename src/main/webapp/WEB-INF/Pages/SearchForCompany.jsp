<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Companies</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/SearchForCompany.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Sidebar.css">
</head>
<body>
  <jsp:include page="header.jsp"/>
    <div class="layout-container">
      
        <div class="main-content">
            <h1 class="heading">Search Companies</h1>
            <p class="heading">Find the companies you're looking for!</p>

            <!-- Search Form -->
            <div class="card-wrapper">
                <div class="form-container">
                    <h2>Search Companies</h2>
                    <form action="${pageContext.request.contextPath}/company" method="post">
                        <div class="form-group">
                            <label for="searchQuery">Search by Name or Location</label>
                            <input type="text" id="searchQuery" name="searchQuery" placeholder="Enter company name or location">
                        </div>
                        <div class="form-group">
                            <button type="submit">Search</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Company List -->
            <div class="card-wrapper">
                <div class="form-container">
                    <h2>Company List</h2>
                    <c:choose>
                        <c:when test="${not empty companyList}">
                            <table class="company-table">
                                <thead>
                                    <tr>
                                        <th>Company Name</th>
                                        <th>Location</th>
                                        <th>Contact</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="company" items="${companyList}">
                                        <tr>
                                            <td>${company.companyName}</td>
                                            <td>${company.companyLocation}</td>
                                            <td>${company.companyContact}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p>No companies found.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>