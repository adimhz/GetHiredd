<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Build Your Profile - Get Hiredd</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ApplyForJob.css">
 
</head>

<body>

<jsp:include page="header.jsp"/>
   <div class="apply-container">
    <form class="apply-form">
   
      <h2>Apply for This Job</h2>

      <div class="form-group">
        <label for="fullname">Full Name</label>
        <input type="text" id="fullname" placeholder="Enter your full name" required>
      </div>

      <div class="form-group">
        <label for="email">Email Address</label>
        <input type="email" id="email" placeholder="Enter your email address" required>
      </div>

      <div class="form-group">
        <label for="resume">Upload Resume</label>
        <input type="file" id="resume" required>
      </div>

      <div class="form-group">
        <label for="coverletter">Cover Letter</label>
        <textarea id="coverletter" placeholder="Write your cover letter..." required></textarea>
      </div>
       </form>
      </div>

      <button type="submit" class="submit-button">Submit Application</button>
  
 <jsp:include page="footer.jsp"/>
</body>
</html>
