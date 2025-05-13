<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/AdminDashboard.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/footer.css">
</head>
<body>
<!-- Wrap everything including footer -->
<div class="layout-container">
  <jsp:include page="SideBar.jsp"/>
  <!-- Main Content -->
  <div class="main-content">
    <div class="top-bar">
      <div class="profile">
        <img
            src="${pageContext.request.contextPath}/resources/images/userProfileImages/${empty sessionScope.profilePicture ? 'default.jpg' : sessionScope.profilePicture}"
            alt="Profile"
            class="profile-img"
            onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/images/userProfileImages/default.jpg';"
          />
        <span>Admin</span>
      </div>
    </div>
    <div class="greeting">
      <h3>Dashboard Overview!</h3>
    </div>
    <div class="dashboard-cards">
      <div class="card">
        <img src="https://via.placeholder.com/60" alt="Icon">
        <h3>Job with Highest Salary</h3>
        <p id="highest-salary-job">Loading...</p>
      </div>
      <div class="card">
        <img src="https://via.placeholder.com/60" alt="Icon">
        <h3>Jobs Posted</h3>
        <p id="jobs-count">27</p>
      </div>
      <div class="card">
        <img src="https://via.placeholder.com/60" alt="Icon">
        <h3>Company with Most Jobs</h3>
        <p id="most-jobs-company">Loading...</p>
      </div>
    </div>
    <!-- Jobs and Companies Table -->
    <div class="jobs-table-section">
      <h3>Jobs and Companies</h3>
      <table id="jobs-table">
        <thead>
          <tr>
            <th>Job Title</th>
            <th>Company</th>
            <th>Job Type</th>
            <th>Salary</th>
            <th>Location</th>
            <th>Deadline</th>
          </tr>
        </thead>
        <tbody id="jobs-table-body">
          <!-- Populated by JavaScript -->
        </tbody>
      </table>
    </div>
  </div>
</div>
<jsp:include page="footer.jsp"/>
<script>
function fetchDashboardData() {
  console.log('Starting fetchDashboardData...');
  fetch('${pageContext.request.contextPath}/dashboard-data')
    .then(response => {
      console.log('Fetch response received:', response);
      if (!response.ok) {
        throw new Error('Network response was not ok: ' + response.statusText);
      }
      return response.text(); // Get raw text first for debugging
    })
    .then(text => {
      console.log('Raw response text:', text);
      const data = JSON.parse(text); // Parse JSON manually
      console.log('Parsed data:', data);

      // Update Jobs Posted
      console.log('Updating jobs count...');
      const jobsCountElement = document.getElementById('jobs-count');
      if (jobsCountElement) {
        jobsCountElement.textContent = data.jobsCount !== undefined ? data.jobsCount : 'N/A';
      } else {
        console.error('jobs-count element not found');
      }

      // Update Highest Salary Job
      console.log('Updating highest salary job...');
      const highestSalaryJobElement = document.getElementById('highest-salary-job');
      if (highestSalaryJobElement) {
        if (data.highestSalaryJob && data.highestSalaryJob.jobTitle && data.highestSalaryJob.jobSalary) {
          highestSalaryJobElement.textContent = data.highestSalaryJob.jobTitle + ' (' + data.highestSalaryJob.jobSalary + ')';
        } else {
          highestSalaryJobElement.textContent = 'No jobs';
        }
      } else {
        console.error('highest-salary-job element not found');
      }

      // Update Most Jobs Company
      console.log('Updating most jobs company...');
      const mostJobsCompanyElement = document.getElementById('most-jobs-company');
      if (mostJobsCompanyElement) {
        if (data.mostJobsCompany && data.mostJobsCompany.companyName && data.mostJobsCompany.jobCount !== undefined) {
          mostJobsCompanyElement.textContent = data.mostJobsCompany.companyName + ' (' + data.mostJobsCompany.jobCount + ')';
        } else {
          mostJobsCompanyElement.textContent = 'No companies';
        }
      } else {
        console.error('most-jobs-company element not found');
      }

      // Update Table
      console.log('Updating jobs table...');
      const tbody = document.getElementById('jobs-table-body');
      if (tbody) {
        tbody.innerHTML = ''; // Clear existing rows
        if (data.jobs && Array.isArray(data.jobs)) {
          console.log('Processing', data.jobs.length, 'jobs');
          data.jobs.forEach((job, index) => {
            console.log('Processing job', index, job);
            const row = document.createElement('tr');
            row.innerHTML = `
              <td>${job.jobTitle || 'N/A'}</td>
              <td>${job.companyName || 'N/A'}</td>
              <td>${job.jobType || 'N/A'}</td>
              <td>${job.jobSalary || 'N/A'}</td>
              <td>${job.jobLocation || 'N/A'}</td>
              <td>${job.jobDeadline || 'N/A'}</td>
            `;
            tbody.appendChild(row);
          });
        } else {
          console.warn('Jobs data is not an array or is missing:', data.jobs);
          tbody.innerHTML = '<tr><td colspan="6">No jobs available</td></tr>';
        }
      } else {
        console.error('jobs-table-body element not found');
      }
    })
    .catch(error => {
      console.error('Error in fetchDashboardData:', error);
      document.getElementById('highest-salary-job').textContent = 'Error';
      document.getElementById('most-jobs-company').textContent = 'Error';
      const tbody = document.getElementById('jobs-table-body');
      if (tbody) {
        tbody.innerHTML = '<tr><td colspan="6">Error loading jobs</td></tr>';
      }
    });
}

// Initial fetch
console.log('Initial fetch starting...');
fetchDashboardData();

// Poll every 30 seconds
setInterval(() => {
  console.log('Polling fetch starting...');
  fetchDashboardData();
}, 30000);
</script>
</body>
</html>