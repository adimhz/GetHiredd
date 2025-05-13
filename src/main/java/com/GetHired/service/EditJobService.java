package com.GetHired.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GetHired.config.DbConfig;
import com.GetHired.model.jobModel;

public class EditJobService {
    private Connection conn;
    private boolean isConnectionError = false;

    public EditJobService() {
        try {
            this.conn = DbConfig.getDbConnection();
        } catch (Exception e) {
            isConnectionError = true;
            e.printStackTrace();
        }
    }

    public jobModel getJobDetailsByTitle(String jobTitle) {
        if (isConnectionError) {
            System.out.println("Database connection error. Unable to fetch job details.");
            return null;
        }

        String query = "SELECT JobTitle, JobType, JobDeadline, JobQualification, JobSalary, JobLocation, JobDescription FROM job WHERE JobTitle = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, jobTitle);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                jobModel job = new jobModel();
                job.setJobTitle(result.getString("JobTitle"));
                job.setJobType(result.getString("JobType"));
                job.setJobDeadline(result.getString("JobDeadline"));
                job.setJobQualification(result.getString("JobQualification"));
                job.setJobSalary(result.getString("JobSalary"));
                job.setJobLocation(result.getString("JobLocation"));
                job.setJobDescription(result.getString("JobDescription"));

                return job;
            } else {
                System.out.println("No job found for title: " + jobTitle);
            }
        } catch (SQLException e) {
            System.out.println("Database error while fetching job details: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public Boolean updateJob(jobModel job) {
        if (conn == null) {
            System.out.println("Database connection is null.");
            return false;
        } else {
            System.out.println("Database connection is established.");
        }

        String updateQuery = "UPDATE job SET JobTitle=?, JobType=?, JobDeadline=?, JobQualification=?, JobSalary=?, JobLocation=?, JobDescription=? WHERE JobTitle = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            updateStmt.setString(1, job.getJobTitle());
            updateStmt.setString(2, job.getJobType());
            updateStmt.setString(3, job.getJobDeadline());
            updateStmt.setString(4, job.getJobQualification());
            updateStmt.setString(5, job.getJobSalary());
            updateStmt.setString(6, job.getJobLocation());
            updateStmt.setString(7, job.getJobDescription());
            updateStmt.setString(8, job.getJobTitle()); // WHERE clause

            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during job update: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
