package com.GetHired.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GetHired.config.DbConfig;
import com.GetHired.model.jobModel;

public class DeleteJobService{
	
	private Connection conn;
	
	public DeleteJobService() {
	try {
		this.conn=DbConfig.getDbConnection();
	} 
	catch (SQLException | ClassNotFoundException ex) {
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
	    String deleteQuery = "DELETE FROM Job WHERE JobTitle = ? AND JobCompanyId = ?";

	    try (
	        PreparedStatement companyStmt = conn.prepareStatement(getCompanyIdQuery);
	        PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)
	    ) {
	        // Step 1: Get CompanyId
	        companyStmt.setString(1, companyName);
	        ResultSet result = companyStmt.executeQuery();
	        if (!result.next()) {
	            System.out.println("Company not found.");
	            return false;
	        }

	        int companyId = result.getInt("CompanyId");

	        // Step 2: Delete job with that title and company
	        deleteStmt.setString(1, jobTitle);
	        deleteStmt.setInt(2, companyId);
	        int rowsDeleted = deleteStmt.executeUpdate();

	        return rowsDeleted > 0;

	    } catch (SQLException e) {
	        System.err.println("Error during job deletion: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}}
