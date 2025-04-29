<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- Adding register as the title of page -->
    <title>Register</title>
        <!-- linking the css file -->
    	<link rel="stylesheet" href="CSS/style.css">
</head>
<body>
    <!-- creating a left section that consists of a logo and a title-->

<div class="login-wrapper">
    <div class="left-section">
        <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" />
        <h1 class="brand">Get Hiredd</h1>
        <h2 class="member-heading">Register Member</h2>
    </div>
    <!-- creating a right section that consists of a text fields to register user-->
    <div class="right-section">
        <form action="${pageContext.request.contextPath}/register" method="post" enctype="multipart/form-data">
            <div class="form-grid">
              <!--text field to enter full name -->
                <div class="form-group">
                    <label for="fullname">Full Name</label>
                    <input type="text" id="fullname" name="fullname" placeholder="Enter your full name" required>
                </div>
                 <!--text field to enter Email -->
                <div class="form-group">
                    <label for="Email">Email</label>
                    <input type="text" id="Email" name="Email" placeholder="Enter your email" required>
                </div>
                 <!--text field to enter Gender -->
                <div class="form-group">
                    <label>Gender</label>
                    <div class="gender-options">
                        <label><input type="radio" name="gender" value="Male" required> Male</label>
                        <label><input type="radio" name="gender" value="Female" required> Female</label>
                        <label><input type="radio" name="gender" value="Other" required> Other</label>
                    </div>
                </div>
                 <!--text field to enter Address -->

                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" id="address" name="address" placeholder="Enter your address" required>
                </div>
 <!--text field to enter Contact number -->
                <div class="form-group">
                    <label for="contactno">Contact Number</label>
                    <input type="text" id="contactno" name="contactno" placeholder="Enter your mobile number" required>
                </div>
                 <!--text field to enter Date of birth -->

                <div class="form-group">
                    <label for="dob">Date of Birth</label>
                    <input type="date" id="dob" name="dob" required>
                </div>
                 <!--text field to enter qualification -->

                <div class="form-group">
                    <label for="qualification">Qualification</label>
                    <input type="text" id="qualification" name="qualification" placeholder="Enter qualification">
                </div>
 				<!--text field to enter experience -->
                <div class="form-group">
                    <label for="experience">Years of Experience</label>
                    <input type="text" id="experience" name="experience" placeholder="Enter experience in years" required min="0">
                </div>
				<!--text field to enter password -->
                <div class="form-group password-wrapper">
                    <label for="password">Password</label>
                    <div class="password-container">
                        <input type="password" id="password" name="password" placeholder="Enter your password" required>
                        <span class="toggle-password" onclick="togglePassword('password', this)">👁️</span>
                    </div>
                </div>
                <!--text field to re confirm password -->
                <div class="form-group password-wrapper">
                    <label for="reconfirmPassword">Re-confirm Password</label>
                    <div class="password-container">
                        <input type="password" id="reconfirmPassword" name="reconfirmPassword" placeholder="Re-enter your password" required>
                        <span class="toggle-password" onclick="togglePassword('reconfirmPassword', this)">👁️</span>
                    </div>
                </div>
				<!--field to upload image for profile picture -->
                <div class="form-group">
                    <label for="profilePic">Upload Profile Picture</label>
                    <input type="file" id="profilePic" name="profilePic" accept="image/*">
                </div>
            </div>
<!--button to submit form -->
            <button type="submit" class="login-button">Register</button>
        </form>
<!--button to redirect it to login  -->
        <p class="note">Already have an account? <a href="login">Login here</a></p>
<!--notes to display error or success message -->
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
<!--to view password in normal text form-->
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