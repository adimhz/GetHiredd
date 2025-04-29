<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GetHired - Homepage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/home.css">
</head>
<body>
    <!-- Navbar Section -->
    <nav class="navbar">
        <div class="navdiv">
            <div class="logo"><a href="#">GetHired</a></div>
            <div class="nav-links">
                <a href="#home">Home</a>
                <a href="AboutUs.html">About Us</a>
                <a href="ContactUs.html">Contact Us</a>
            </div>
        </div>
    </nav>

    <!-- Homepage Section -->
    <section id="home" class="hero">
        <h2>Welcome to GetHired</h2>
        <p>Your gateway to amazing career opportunities!</p>
    </section>

    <!-- About Us Section -->
    <section id="about" class="info-section">
        <h2>About Us</h2>
        <p>GetHired is a leading platform that connects job seekers with top companies. Our mission is to help you land your dream job by providing access to job listings, career resources, and professional networking.</p>
    </section>

    <!-- Register/Login Section -->
    <section id="register-login" class="info-section">
        <h2>Register / Login</h2>
        <div class="form-container">
          <a href="register">  <button class="btn">Register</button></a>
           <a href="login"> <button class="btn">Login</button></a>
        </div>
    </section>

    <!-- Contact Us Section -->
    <section id="contact" class="info-section">
        <h2>Contact Us</h2>
        <p>If you have any questions, feel free to reach out to us at <a href="mailto:contact@gethired.com">contact@gethired.com</a>.</p>
    </section>

    <!-- Footer -->
<jsp:include page="footer.jsp"/>
</body>
</html>
