<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Build Your Profile - Get Hiredd</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/BuildYourProfile.css">
  
</head>

<body>

<jsp:include page="header.jsp"/>
  <main>
    <div class="profile-form-container">
      <h1>Build Your Profile</h1>
      <form>
        <div class="form-group">
          <label for="fullname">Full Name</label>
          <input type="text" id="fullname" placeholder="Enter your full name" required>
        </div>

        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" placeholder="Enter your email" required>
        </div>

        <div class="form-group">
          <label for="phone">Phone Number</label>
          <input type="tel" id="phone" placeholder="Enter your phone number" required>
        </div>

        <div class="form-group">
          <label for="location">Location</label>
          <input type="text" id="location" placeholder="Enter your location">
        </div>

        <div class="form-group">
          <label for="skills">Skills</label>
          <input type="text" id="skills" placeholder="E.g., HTML, CSS, JavaScript">
        </div>

        <div class="form-group">
          <label for="experience">Experience</label>
          <input type="text" id="experience" placeholder="Years of experience">
        </div>

        <div class="form-group">
          <label for="bio">About You</label>
          <textarea id="bio" placeholder="Write a short bio about yourself"></textarea>
        </div>

        <div class="form-group">
          <label for="resume">Upload Resume</label>
          <input type="file" id="resume">
        </div>

        <button type="submit" class="save-button">Save Profile</button>
      </form>
    </div>
  </main>
  <jsp:include page="footer.jsp"/>
</body>
</html>
