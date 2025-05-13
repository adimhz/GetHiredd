package com.GetHired.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.GetHired.config.DbConfig;
import com.GetHired.model.Company;
import com.GetHired.model.jobModel;

public class CreateJobsService {
    private Connection conn;

    public CreateJobsService() {
        try {
            this.conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Boolean createjob(jobModel job) {
        if (conn == null) {
            System.out.println("Database connection is null.");
            return false;
        } else {
            System.out.println("Database connection is established.");
        }
        String CompanyQuery = "SELECT CompanyId FROM company WHERE CompanyName = ?";
        String insertQuery = "INSERT INTO Job(JobTitle,JobType,JobDeadline,JobQualification,JobCompanyId,JobSalary,JobLocation,JobDescription)"
                + "VALUES (?,?,?,?,?,?,?,?)";

        try (PreparedStatement CompanyStmt = conn.prepareStatement(CompanyQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            // Fetch company ID
            CompanyStmt.setString(1, job.getJobCompanyId().getCompanyName());
            ResultSet result = CompanyStmt.executeQuery();
            int CompanyId = result.next() ? result.getInt("CompanyId") : 1;

            insertStmt.setString(1, job.getJobTitle());
            insertStmt.setString(2, job.getJobType());
            insertStmt.setDate(3, Date.valueOf(job.getJobDeadline()));
            insertStmt.setString(4, job.getJobQualification());
            insertStmt.setInt(5, CompanyId);
            insertStmt.setString(6, job.getJobSalary());
            insertStmt.setString(7, job.getJobLocation());
            insertStmt.setString(8, job.getJobDescription());

            return insertStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during job creation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<jobModel> getAllJobs() {
        List<jobModel> jobs = new ArrayList<>();
        String query = "SELECT j.JobTitle, j.JobType, j.JobDeadline, j.JobSalary, j.JobLocation, j.JobDescription, c.CompanyName " +
                       "FROM Job j JOIN company c ON j.JobCompanyId = c.CompanyId";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Company company = new Company(rs.getString("CompanyName"));
                jobModel job = new jobModel(
                    rs.getString("JobTitle"),
                    rs.getString("JobType"),
                    rs.getDate("JobDeadline").toString(),
                    null, // Qualification not needed for table
                    company,
                    rs.getString("JobSalary"),
                    rs.getString("JobLocation"),
                    rs.getString("JobDescription")
                );
                jobs.add(job);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching jobs: " + e.getMessage());
            e.printStackTrace();
        }
        return jobs;
    }

    public int getJobsCount() {
        String query = "SELECT COUNT(*) FROM Job";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching jobs count: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public jobModel getHighestSalaryJob() {
        String query = "SELECT j.JobTitle, j.JobSalary, c.CompanyName " +
                       "FROM Job j JOIN company c ON j.JobCompanyId = c.CompanyId " +
                       "ORDER BY CAST(j.JobSalary AS DECIMAL) DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Company company = new Company(rs.getString("CompanyName"));
                return new jobModel(
                    rs.getString("JobTitle"),
                    null, null, null, company,
                    rs.getString("JobSalary"),
                    null, null
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching highest salary job: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Company getCompanyWithMostJobs() {
        String query = "SELECT c.CompanyName, COUNT(j.JobId) as jobCount " +
                       "FROM company c LEFT JOIN Job j ON c.CompanyId = j.JobCompanyId " +
                       "GROUP BY c.CompanyId, c.CompanyName " +
                       "ORDER BY jobCount DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Company company = new Company(rs.getString("CompanyName"));
                company.setJobCount(rs.getInt("jobCount"));
                return company;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching company with most jobs: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}