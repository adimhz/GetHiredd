<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creating Job</title>
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
        <!-- Create Job Form -->
        <div class="form-container">
            <h2>Create a Job</h2>
            <form action="${pageContext.request.contextPath}/createjobs" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="CreateJobTitle">Job Title</label>
                    <input type="text" id="CreateJobTitle" name="CreateJobTitle" value="${CreateJobTitle != null ? CreateJobTitle : ''}" required>
                    <c:if test="${not empty errors['CreateJobTitle']}"><span style="color: red;">${errors['CreateJobTitle']}</span></c:if>
                </div>
                <div class="form-group">
                    <label for="JobType">Job Type</label>
                    <input type="text" id="JobType" name="JobType" value="${JobType != null ? JobType : ''}" required>
                    <c:if test="${not empty errors['JobType']}"><span style="color: red;">${errors['JobType']}</span></c:if>
                </div>
                <div class="form-group">
                    <label for="Deadline">Deadline</label>
                    <input type="date" id="Deadline" name="Deadline" value="${Deadline != null ? Deadline : ''}" required>
                    <c:if test="${not empty errors['Deadline']}"><span style="color: red;">${errors['Deadline']}</span></c:if>
                </div>
                <div class="form-group">
                    <label for="Qualification">Qualification</label>
                    <input type="text" id="Qualification" name="Qualification" value="${Qualification != null ? Qualification : ''}" required>
                    <c:if test="${not empty errors['Qualification']}"><span style="color: red;">${errors['Qualification']}</span></c:if>
                </div>
                <div class="form-group">
                    <label for="company">Company</label>
                    <input type="text" id="company" name="Company" value="${Company != null ? Company : ''}" required>
                    <c:if test="${not empty errors['Company']}"><span style="color: red;">${errors['Company']}</span></c:if>
                </div>
                <div class="form-group">
                    <label for="salary">Salary</label>
                    <input type="text" id="salary" name="Salary" value="${Salary != null ? Salary : ''}" required>
                    <c:if test="${not empty errors['Salary']}"><span style="color: red;">${errors['Salary']}</span></c:if>
                </div>
                <div class="form-group">
                    <label for="location">Location</label>
                    <input type="text" id="location" name="Location" value="${Location != null ? Location : ''}" required>
                    <c:if test="${not empty errors['Location']}"><span style="color: red;">${errors['Location']}</span></c:if>
                </div>
                <div class="form-group">
                    <label for="description">Job Description</label>
                    <textarea id="description" name="description" rows="5" required>${description != null ? description : ''}</textarea>
                    <c:if test="${not empty errors['description']}"><span style="color: red;">${errors['description']}</span></c:if>
                </div>
                <div class="form-group">
                    <button type="submit">Create</button>
                </div>
            </form>
            <c:if test="${not empty error}">
                <p style="color: red;">${error}</p>
            </c:if>
            <c:if test="${not empty success}">
                <p style="color: green;">${success}</p>
            </c:if>
        </div>
    </div>
</div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>