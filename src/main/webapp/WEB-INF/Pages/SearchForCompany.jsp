<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Jobs - Get Hiredd</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', sans-serif;
      text-decoration: none;
    }

    body {
      background:linear-gradient(to right,#cdb7ad,#ffffff);
       min-height: 100vh;
      display: flex;
      flex-direction: column;
    }

    /* Navbar */
    .navbar {
      background: #000;
      padding: 20px 40px;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
    }

    .navdiv {
      display: flex;
      align-items: center;
      justify-content: space-between;
      flex-wrap: wrap;
    }

    .logo a {
      font-size: 28px;
      font-weight: bold;
      color: white;
    }

    ul {
      display: flex;
      align-items: center;
      gap: 20px;
      flex-wrap: wrap;
    }

    ul li {
      list-style: none;
    }

    ul li a {
      color: #f9f9f9;
      font-size: 16px;
      font-weight: 600;
      transition: color 0.3s ease;
    }

    ul li a:hover {
      color: #ff6a6a;
    }

    .buttons {
      display: flex;
      gap: 12px;
      margin-top: 10px;
    }

    .button {
      background-color: #ff6a6a;
      border: 1px solid #ccc;
      padding: 8px 16px;
      border-radius: 8px;
      transition: background-color 0.3s ease;
    }

    .button a {
      color: #333;
      font-weight: bold;
      font-size: 14px;
    }

    .button:hover {
      background-color: #f0f0f0;
    }

    /* Main Section */
    main {
      padding: 50px 20px;
      flex: 1;
    }

    .logo a {
      font-size: 28px;
      font-weight: bold;
      color: white;
    }

    ul {
      display: flex;
      align-items: center;
      gap: 20px;
      flex-wrap: wrap;
    }

    ul li {
      list-style: none;
    }

    ul li a {
      color: #f9f9f9;
      font-size: 16px;
      font-weight: 600;
      transition: color 0.3s ease;
    }

    ul li a:hover {
      color: #ff6a6a;
    }

    /* Main Section */
    main {
      padding: 50px 20px;
      flex: 1;
    }

    .company-title {
      text-align: center;
      font-size: 36px;
      margin-bottom: 40px;
      color: #333;
    }

    .search-bar {
      display: block;
      margin: 0 auto 40px;
      padding: 12px 20px;
      width: 350px;
      max-width: 90%;
      border: 1px solid #bbb;
      border-radius: 10px;
      font-size: 16px;
      background: #f5f5f5;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
      transition: border-color 0.3s ease;
    }

    .search-bar:focus {
      border-color: #1976d2;
      outline: none;
    }

    /* Company Cards */
    .companies-grid {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
      justify-content: center;
    }

    .company-card {
      background: #fff;
      border: 1px solid #ddd;
      border-radius: 12px;
      width: 300px;
      padding: 20px;
      text-align: center;
      transition: transform 0.3s ease;
      box-shadow: 0 8px 16px rgba(0,0,0,0.1);
    }

    .company-card:hover {
      transform: translateY(-5px);
    }

    .company-logo {
  width: 80px;
  height: 80px;
  background: #eee;
  border-radius: 50%;
  margin: 0 auto 20px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

.company-logo img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}


    .company-name {
      font-size: 20px;
      color: #333;
      margin-bottom: 8px;
    }

    .company-location {
      font-size: 14px;
      color: #777;
      margin-bottom: 15px;
    }

    .view-button {
      background-color: #1976d2;
      color: white;
      padding: 8px 20px;
      border: none;
      border-radius: 6px;
      font-size: 14px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .view-button:hover {
      background-color: #155a9c;
    }



  

    .footer-container {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  padding: 40px;
  background-color: #111;
  color: #fff;
}

/* Inside left section - two small columns */
.footer-left {
  display: flex;
  width: 48%;
  gap: 70px; 
  padding-top: 80px;
  padding-left: 50px;
}

/* Logo and tagline stacked */
.footer-logo-tagline {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* Footer links in a vertical list */
.footer-links {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.footer-links a {
  color: #ccc;
  text-decoration: none;
  font-size: 14px;
}

.footer-links a:hover {
  color: #fff;
}

/* Right side with map */
.footer-right {
  width: 48%;
}

.footer-right iframe {
  width: 100%;
  height: 250px;
  border-radius: 10px;
}

/* Bottom copyright */
.footer-bottom {
  text-align: center;
  padding: 20px;
  background-color: #000;
  color: #777;
  font-size: 14px;
}



    @media (max-width: 768px) {
      .navdiv, .footer-container, .job-card {
        flex-direction: column;
        align-items: center;
        text-align: center;
      }
      ul {
        flex-direction: column;
        margin-top: 10px;
      }
      .buttons {
        flex-direction: column;
        width: 100%;
      }
      .job-info {
        flex-direction: column;
      }
      .apply-section {
        margin-top: 10px;
      }}
      /* Search and Sort Bar */
.search-sort-container {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  margin-bottom: 30px;
  flex-wrap: wrap;
}

.search-bar {
  padding: 10px 20px;
  width: 600px;
  max-width: 90%;
  border: 1px solid #a15b5b;
  border-radius: 8px;
  font-size: 16px;
  background: #e6dfdf;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
  transition: border-color 0.3s ease;
}

.search-bar:focus {
  border-color: #1976d2;
  outline: none;
}

.sort-bar {
  padding: 10px 20px;
  width: 200px;
  max-width: 90%;
  border: 1px solid #dfdddd;
  border-radius: 8px;
  font-size: 16px;
  background: #d06464;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
  transition: border-color 0.3s ease;
}

.sort-bar:focus {
  border-color: #1976d2;
  outline: none;
}
.nav-profile {
  display: flex;
  align-items: center;
  gap: 15px;
}

.profile-pic {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid #fff;
}

.logout-button {
  background-color: #ff4d4d;
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.logout-button:hover {
  background-color: #d93636;
}

@media (max-width: 768px) {
  .search-sort-container {
    flex-direction: column;
  }

  .search-bar, .sort-bar {
    width: 100%;
  }
}


  </style>
</head>

<body>

  <!-- Navbar -->
<!-- Navbar -->
<nav class="navbar">
    <div class="navdiv">
      <div class="logo"><a href="#">Get Hiredd</a></div>
      <ul>
        <li><a href="SearchForJob.html">Search for job</a></li>
        <li><a href="SearchForCompany.html">Search for company</a></li>
        <li><a href="BuildYourProfile.html">Build your profile</a></li>
        <li><a href="AboutUs.html">About Us</a></li>
        <li><a href="ContactUs.html">Contact Us</a></li>
      </ul>
      <div class="nav-profile">
        <a href="ViewProfile.html">
            <img src="profile.jpg" alt="Profile" class="profile-pic">
          </a>
        <button class="logout-button">Log Out</button>
      </div>
    </div>
  </nav>
  <main>
    <h1 class="company-title">Search Companies</h1>
    
    <input type="text" class="search-bar" placeholder="Search for companies...">

    <div class="companies-grid">

      <div class="company-card">
        <div class="company-logo"><img src="${pageContext.request.contextPath}/resources/images/Company4.png"></div>
        <div class="company-name">We Are Working Pvt.Ltd</div>
        <div class="company-location">Lalitpur, Nepal</div>
        <button class="view-button">View Jobs</button>
      </div>

      <div class="company-card">
        <div class="company-logo"><img src="${pageContext.request.contextPath}/resources/images/Company3.png"></div>
        <div class="company-name">Creative Solutions Ltd</div>
        <div class="company-location">Kathmandu, Nepal</div>
        <button class="view-button">View Jobs</button>
      </div>

      <div class="company-card">
        <div class="company-logo"><img src="${pageContext.request.contextPath}/resources/images/Company2.png"></div>
        <div class="company-name">NextGen Innovations</div>
        <div class="company-location">Pokhara, Nepal</div>
        <button class="view-button">View Jobs</button>
      </div>

      <div class="company-card">
        <div class="company-logo"><img src="${pageContext.request.contextPath}/resources/images/Company1.png"></div>
        <div class="company-name">Skyline Pvt Ltd</div>
        <div class="company-location">Bhaktapur, Nepal</div>
        <button class="view-button">View Jobs</button>
      </div>

    </div>

  </main>

  <footer>
    <div class="footer-container">
      <!-- Left side with two columns inside -->
      <div class="footer-left">
        <div class="footer-logo-tagline">
          <h2 class="footer-logo">Get Hiredd</h2>
          <p class="footer-tagline">Connecting talent with opportunity.</p>
        </div>
  
        <div class="footer-links">
          <a href="#">Privacy Policy</a>
          <a href="#">Terms of Service</a>
          <a href="#">Contact Us</a>
        </div>
      </div>
  
      <!-- Right side with map -->
      <div class="footer-right">
        <iframe 
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3532.2394605105936!2d85.3240!3d27.7089!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39eb190a12345678%3A0x1234abcd5678ef90!2sYour%20Location!5e0!3m2!1sen!2snp!4v1612345678901" 
          width="100%" 
          height="100%" 
          style="border:0;" 
          allowfullscreen="" 
          loading="lazy"
          referrerpolicy="no-referrer-when-downgrade">
        </iframe>
      </div>
    </div>
  
    <div class="footer-bottom">
      &copy; 2025 Get Hiredd. All rights reserved.
    </div>
  </footer>
  
  
  

</body>
</html>
