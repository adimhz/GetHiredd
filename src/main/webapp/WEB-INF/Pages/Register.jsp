<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
    <div class="login-wrapper">
        <div class="left-section">
            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" />
            <h1 class="brand">Get Hiredd</h1>
            <h2 class="member-heading">Register Member</h2>
        </div>
        <div class="right-section">
            <form action="${pageContext.request.contextPath}/register" method="post" enctype="multipart/form-data">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="fullname">Full Name</label>
                        <input type="text" id="fullname" name="fullname" placeholder="Enter your full name" value="${fullname}" required>
                    </div>
                    <div class="form-group">
                        <label for="Email">Email</label>
                        <input type="text" id="Email" name="Email" placeholder="Enter your email" value="${Email}" required>
                    </div>
                    <div class="form-group">
                        <label>Gender</label>
                        <div class="gender-options">
                            <label><input type="radio" name="gender" value="Male" ${gender == 'Male' ? 'checked' : ''} required> Male</label>
                            <label><input type="radio" name="gender" value="Female" ${gender == 'Female' ? 'checked' : ''} required> Female</label>
                            <label><input type="radio" name="gender" value="Other" ${gender == 'Other' ? 'checked' : ''} required> Other</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" id="address" name="address" placeholder="Enter your address" value="${address}" required>
                    </div>
                    <div class="form-group">
                        <label for="contactno">Contact Number</label>
                        <input type="text" id="contactno" name="contactno" placeholder="Enter your mobile number" value="${contactno}" required>
                    </div>
                    <div class="form-group">
                        <label for="dob">Date of Birth</label>
                        <input type="date" id="dob" name="dob" value="${dob}" required>
                    </div>
                    <div class="form-group">
                        <label for="qualification">Qualification</label>
                        <input type="text" id="qualification" name="qualification" placeholder="Enter qualification" value="${qualification}">
                    </div>
                    <div class="form-group">
                        <label for="experience">Years of Experience</label>
                        <input type="text" id="experience" name="experience" placeholder="Enter experience in years" value="${experience}" required min="0">
                    </div>
                    <div class="form-group password-wrapper">
                        <label for="password">Password</label>
                        <div class="password-container">
                            <input type="password" id="password" name="password" placeholder="Enter your password" required>
                            <span class="toggle-password" onclick="togglePassword('password', this)">👁️</span>
                        </div>
                    </div>
                    <div class="form-group password-wrapper">
                        <label for="reconfirmPassword">Re-confirm Password</label>
                        <div class="password-container">
                            <input type="password" id="reconfirmPassword" name="reconfirmPassword" placeholder="Re-enter your password" required>
                            <span class="toggle-password" onclick="togglePassword('reconfirmPassword', this)">👁️</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="profilePic">Upload Profile Picture</label>
                        <input type="file" id="profilePic" name="profilePic" accept="image/*">
                    </div>
                </div>
                <button type="submit" class="login-button">Register</button>
            </form>
            <p class="note">Already have an account? <a href="login">Login here</a></p>
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
    <script>
        function togglePassword(id, element) {
            const input = document.getElementById(id);
            if (input.type === "password") {
                input.type = "text";
                element.textContent = "🙈";
            } else {
                input.type = "password";
                element.textContent = "👁️";
            }
        }
    </script>
</body>
</html>