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
                <a href="aboutus">About Us</a>
                <a href="contact">Contact Us</a>
                <a href="register" class="btn-link"><button class="btn">Register</button></a>
                <a href="login" class="btn-link"><button class="btn">Login</button></a>
            </div>
        </div>
    </nav>
    <!-- Homepage Section -->
    <section id="home" class="hero">
        <h2>Welcome to GetHired</h2>
        <p>Your gateway to amazing career opportunities!</p>
    </section>

   <jsp:include page="footer.jsp"/>
</body>
</html>
