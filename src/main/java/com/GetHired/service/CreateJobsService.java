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

    public Boolean createJob(jobModel job) {
        if (conn == null) {
            System.out.println("Database connection is null.");
            return false;
        }
        String companyQuery = "SELECT CompanyId FROM company WHERE CompanyName = ?";
        String insertQuery = "INSERT INTO job(JobTitle, JobType, JobDeadline, JobQualification, JobCompanyId, JobSalary, JobLocation, JobDescription)"
                + " VALUES (?,?,?,?,?,?,?,?)";

        try (PreparedStatement companyStmt = conn.prepareStatement(companyQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            companyStmt.setString(1, job.getJobCompanyId().getCompanyName());
            ResultSet result = companyStmt.executeQuery();
            if (!result.next()) {
                System.err.println("Company not found: " + job.getJobCompanyId().getCompanyName());
                return false;
            }
            int companyId = result.getInt("CompanyId");

            insertStmt.setString(1, job.getJobTitle());
            insertStmt.setString(2, job.getJobType());
            insertStmt.setDate(3, Date.valueOf(job.getJobDeadline()));
            insertStmt.setString(4, job.getJobQualification());
            insertStmt.setInt(5, companyId);
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
                       "FROM job j JOIN company c ON j.JobCompanyId = c.CompanyId";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Company company = new Company(rs.getString("CompanyName"));
                jobModel job = new jobModel(
                    rs.getString("JobTitle"),
                    rs.getString("JobType"),
                    rs.getDate("JobDeadline").toString(),
                    null,
                    company,
                    rs.getString("JobSalary"),
                    rs.getString("JobLocation"),
                    rs.getString("JobDescription")
                );
                jobs.add(job);
            }
            System.out.println("Fetched " + jobs.size() + " jobs");
        } catch (SQLException e) {
            System.err.println("Error fetching jobs: " + e.getMessage());
            e.printStackTrace();
        }
        return jobs;
    }

}