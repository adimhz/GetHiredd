<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
<nav class="navbar">
  <div class="navdiv">
    <div class="logo"><a href="#">Get Hiredd</a></div>
    <ul>
      <li><a href="jobs">Search for job</a></li>
      <li><a href="company">Search for company</a></li>
      <li><a href="buildYourProfile">Build your profile</a></li>
      <li><a href="aboutus">About Us</a></li>
      <li><a href="contact">Contact Us</a></li>
    </ul>
    <div class="nav-profile">
    <img src="${pageContext.request.contextPath}/userProfileImages/${empty sessionScope.profilePicture ? 'default.png' : sessionScope.profilePicture}" 
     alt="User Profile" >
          <button class="logout-button">Log Out</button>
    </div>
  </div>
</nav>
</header>

<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/header.css" >