package com.GetHired.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GetHired.config.DbConfig;

public class ApplicationService {
    private Connection conn;

    public ApplicationService() {
        try {
            this.conn = DbConfig.getDbConnection();
            System.out.println("ApplicationService: Database connection initialized");
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("ApplicationService: Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean applyForJob(String userId, int jobId) {
        System.out.println("ApplicationService: Applying for job, userId = " + userId + ", jobId = " + jobId);
        try (Connection conn = DbConfig.getDbConnection()) {
            // Validate jobId exists in job table
            if (!jobExists(jobId, conn)) {
                System.out.println("ApplicationService: JobId = " + jobId + " does not exist in job table");
                return false;
            }
            if (hasApplied(userId, jobId, conn)) {
                System.out.println("ApplicationService: User has already applied for jobId = " + jobId);
                return false; // Already applied
            }
            String query = "INSERT INTO user_job (Email, JobId) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, userId);
                stmt.setInt(2, jobId);
                int rowsAffected = stmt.executeUpdate();
                System.out.println("ApplicationService: Rows affected = " + rowsAffected);
                return rowsAffected > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ApplicationService: Error applying for job: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasApplied(String userId, int jobId) {
        System.out.println("ApplicationService: Checking if userId = " + userId + " has applied for jobId = " + jobId);
        try (Connection conn = DbConfig.getDbConnection()) {
            return hasApplied(userId, jobId, conn);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ApplicationService: Error checking application status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean hasApplied(String userId, int jobId, Connection conn) throws SQLException {
        String query = "SELECT COUNT(*) FROM user_job WHERE Email = ? AND JobId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userId);
            stmt.setInt(2, jobId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("ApplicationService: Application count = " + count);
                    return count > 0;
                }
            }
        }
        return false;
    }

    private boolean jobExists(int jobId, Connection conn) throws SQLException {
        String query = "SELECT COUNT(*) FROM job WHERE JobId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, jobId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("ApplicationService: JobId = " + jobId + " exists = " + (count > 0));
                    return count > 0;
                }
            }
        }
        return false;
    }
}