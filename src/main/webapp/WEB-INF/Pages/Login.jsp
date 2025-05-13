<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="CSS/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<div class="login-wrapper">
    <div class="left-section">
        <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" />
<h1 class="brand">Get Hiredd</h1>
        <h2 class="member-heading">Member Login</h2>
    </div>

    <div class="right-section">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-grid">
                <div class="form-group">
                    <label for="Email">Email</label>
                    <input type="text" id="Email" name="Email" placeholder="Enter your email" required>
                </div>

                 <div class="form-group password-wrapper">
                    <label for="password">Password</label>
                    <div class="password-container">
                        <input type="password" id="password" name="password" placeholder="Enter your password" required>
                        <span class="toggle-password" onclick="togglePassword('password', this)">👁️</span>
                    </div>
                </div>
            </div>

            <button type="submit" class="login-button">Login</button>
        </form>

        <p class="note">Already have an account? <a href="register">Register here</a></p>

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
        element.textContent = "🙈"; // 🙈
    } else {
        input.type = "password";
        element.textContent = "👁️"; // 👁️
    }
}

</script>

</body>
</html>
