package com.GetHired.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.GetHired.config.DbConfig;
import com.GetHired.model.Company;
;

public class CreateCompanyService{
	
	private Connection conn;
	
	public CreateCompanyService() {
	try {
		this.conn=DbConfig.getDbConnection();
	} 
	catch (SQLException | ClassNotFoundException ex) {
		System.err.println("Database connection error: " + ex.getMessage());
		ex.printStackTrace();
	}
}
	public Boolean createcompany(Company company) {
		if (conn == null) {
		    System.out.println("Database connection is null.");
		    return false;
		} else {
		    System.out.println("Database connection is established.");
		}
		String insertQuery = "INSERT INTO company(CompanyName,CompanyLocation,CompanyContact)"
				+ "VALUES (?,?,?)";
		try(PreparedStatement insertStmt = conn.prepareStatement(insertQuery)){
			insertStmt.setString(1, company.getCompanyName());
			insertStmt.setString(2, company.getCompanyLocation());
			insertStmt.setString(3, company.getCompanyContact());
			
			return insertStmt.executeUpdate() > 0;
		}
		 catch (SQLException e) {
				System.err.println("Error during adding company: " + e.getMessage());
				e.printStackTrace();
				return false;
			}
			
		}
	}