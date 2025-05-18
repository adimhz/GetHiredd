<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>User Registration</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/BuildYourProfile.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/header.css">
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Arial', sans-serif;
    }

    body {
      background: linear-gradient(to right, #e2b39d, #cdb7ad);
      color: #333;
      min-height: 100vh;
      display: flex;
      flex-direction: column;
    }

    .form-wrapper {
      flex-grow: 1;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 50px 0;
    }

    .form-container {
      background-color: #222;
      padding: 30px;
      border-radius: 15px;
      box-shadow: 0 12px 32px rgba(0, 0, 0, 0.2);
      max-width: 700px;
      width: 90%;
    }

    .form-container h2 {
      text-align: center;
      margin-bottom: 20px;
      color: white;
    }

    .form-group {
      margin-bottom: 15px;
    }

    label {
      font-size: 14px;
      color: #ccc;
      margin-bottom: 5px;
      display: block;
    }

    input[type="text"],
    input[type="password"],
    input[type="date"],
    input[type="file"],
    textarea {
      width: 100%;
      padding: 10px;
      border: 1px solid #444;
      border-radius: 8px;
      background-color: #444;
      color: white;
      font-size: 14px;
    }

    .gender-options {
      display: flex;
      gap: 10px;
      margin-top: 5px;
    }

    .gender-options label {
      color: #ccc;
    }

    input:focus,
    textarea:focus {
      border-color: #555;
      background-color: #555;
      outline: none;
    }

    .password-container {
      display: flex;
      align-items: center;
      position: relative;
    }

    .toggle-password {
      position: absolute;
      right: 10px;
      cursor: pointer;
      color: #ccc;
    }

    button {
      width: 100%;
      padding: 10px;
      background-color: #44506f;
      color: white;
      font-size: 14px;
      border: none;
      border-radius: 8px;
      cursor: pointer;
    }

    button:hover {
      background-color: #555;
    }
  </style>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="form-wrapper">
  <div class="form-container">
    <h2>User Registration</h2>
    <form action="${pageContext.request.contextPath}/buildYourProfile" method="post" enctype="multipart/form-data">
      <div class="form-group">
        <label for="fullname">Full Name</label>
        <input type="text" id="fullname" name="fullname" value="${user.fullName}" required>
      </div>
      <div class="form-group">
        <label for="Email">Email</label>
        <input type="text" id="Email" name="Email" value="${user.email}" required>
      </div>
      <div class="form-group">
      <div class="gender-options">
  <label><input type="radio" name="gender" value="Male" ${user.gender == 'Male' ? 'checked' : ''} required> Male</label>
  <label><input type="radio" name="gender" value="Female" ${user.gender == 'Female' ? 'checked' : ''} required> Female</label>
  <label><input type="radio" name="gender" value="Other" ${user.gender == 'Other' ? 'checked' : ''} required> Other</label>
</div>

      </div>
      <div class="form-group">
        <label for="address">Address</label>
        <input type="text" id="address" name="address" value="${user.address}" required>
      </div>
      <div class="form-group">
        <label for="contactno">Contact Number</label>
        <input type="text" id="contactno" name="contactno"  value="${user.contactNo}" required>
      </div>
      <div class="form-group">
        <label for="dob">Date of Birth</label>
        <input type="date" id="dob" name="dob" required value="${user.dateOfBirth}" required>
      </div>
      <div class="form-group">
        <label for="qualification">Qualification</label>
        <input type="text" id="qualification" name="qualification"  value="${user.qualification}">
      </div>
      <div class="form-group">
        <label for="experience">Years of Experience</label>
        <input type="text" id="experience" name="experience" value="${user.yearsOfExperience}" required>
      </div>>

      <div class="form-group">
        <label for="profilePic">Upload Profile Picture</label>
        <input type="file" id="profilePic" name="profilePic" accept="image/*" value="${user.imageUrl}">
      </div>
      <div class="form-group">
        <button type="submit">Save</button>
      </div>
    </form>
 
	
           <% String message = (String) request.getAttribute("message");
           if (message != null) { %>
           <p style="color: green;"><%= message %></p>
        <% } %>
         </div>
</div>
<jsp:include page="footer.jsp"/>

<script>
  function togglePassword(id, el) {
    const input = document.getElementById(id);
    if (input.type === "password") {
      input.type = "text";
      el.textContent = "🙈";
    } else {
      input.type = "password";
      el.textContent = "👁️";
    }
  }
</script>
</body>
</html>
