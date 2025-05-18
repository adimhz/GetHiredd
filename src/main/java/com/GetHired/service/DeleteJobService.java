package com.GetHired.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GetHired.config.DbConfig;

public class DeleteJobService {

    private Connection conn;

    public DeleteJobService() {
        try {
            this.conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Boolean deleteJob(String jobTitle, String companyName) {
        if (conn == null) {
            System.out.println("Database connection is null.");
            return false;
        } else {
            System.out.println("Database connection is established.");
        }

        String getCompanyIdQuery = "SELECT CompanyId FROM company WHERE CompanyName = ?";
        String getJobIdQuery = "SELECT JobId FROM job WHERE JobTitle = ? AND JobCompanyId = ?";
        String deleteUserJobQuery = "DELETE FROM user_job WHERE JobId = ?";
        String deleteJobQuery = "DELETE FROM job WHERE JobId = ?";

        try (
            PreparedStatement getCompanyIdStmt = conn.prepareStatement(getCompanyIdQuery);
            PreparedStatement getJobIdStmt = conn.prepareStatement(getJobIdQuery);
            PreparedStatement deleteUserJobStmt = conn.prepareStatement(deleteUserJobQuery);
            PreparedStatement deleteJobStmt = conn.prepareStatement(deleteJobQuery)
        ) {
            // Step 1: Get CompanyId
            getCompanyIdStmt.setString(1, companyName);
            ResultSet companyResult = getCompanyIdStmt.executeQuery();
            if (!companyResult.next()) {
                System.out.println("Company not found.");
                return false;
            }

            int companyId = companyResult.getInt("CompanyId");

            // Step 2: Get JobId
            getJobIdStmt.setString(1, jobTitle);
            getJobIdStmt.setInt(2, companyId);
            ResultSet jobResult = getJobIdStmt.executeQuery();
            if (!jobResult.next()) {
                System.out.println("Job not found.");
                return false;
            }

            int jobId = jobResult.getInt("JobId");

            // Step 3: Delete entries in user_job referencing this job
            deleteUserJobStmt.setInt(1, jobId);
            deleteUserJobStmt.executeUpdate();

            // Step 4: Delete the job itself
            deleteJobStmt.setInt(1, jobId);
            int rowsDeleted = deleteJobStmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Error during job deletion: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
