package com.GetHired.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GetHired.config.DbConfig;

public class DeleteCompanyService {

    private Connection conn;

    public DeleteCompanyService() {
        try {
            this.conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean deleteCompany(String companyName, String companyContact) {
        if (conn == null) {
            System.out.println("Database connection is null.");
            return false;
        } else {
            System.out.println("Database connection is established.");
        }

        String getCompanyIdQuery = "SELECT CompanyId FROM company WHERE CompanyName = ? AND CompanyContact = ?";
        String deleteJobsQuery = "DELETE FROM job WHERE JobCompanyId = ?";
        String deleteCompanyQuery = "DELETE FROM company WHERE CompanyId = ?";

        try (
            PreparedStatement getCompanyIdStmt = conn.prepareStatement(getCompanyIdQuery);
            PreparedStatement deleteJobsStmt = conn.prepareStatement(deleteJobsQuery);
            PreparedStatement deleteCompanyStmt = conn.prepareStatement(deleteCompanyQuery)
        ) {
            // Step 1: Get CompanyId
            getCompanyIdStmt.setString(1, companyName);
            getCompanyIdStmt.setString(2, companyContact);
            ResultSet result = getCompanyIdStmt.executeQuery();

            if (!result.next()) {
                System.out.println("Company not found.");
                return false;
            }

            int companyId = result.getInt("CompanyId");

            // Step 2: Delete related jobs
            deleteJobsStmt.setInt(1, companyId);
            deleteJobsStmt.executeUpdate(); // Optional: check affected rows if needed

            // Step 3: Delete the company
            deleteCompanyStmt.setInt(1, companyId);
            int rowsDeleted = deleteCompanyStmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Error during company deletion: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
