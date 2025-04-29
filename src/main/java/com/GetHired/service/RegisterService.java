package com.GetHired.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.GetHired.config.DbConfig;
import com.GetHired.model.UserModel;

public class RegisterService{
	
	private Connection conn;
	
	public RegisterService() {
	try {
		this.conn=DbConfig.getDbConnection();
	} 
	catch (SQLException | ClassNotFoundException ex) {
		System.err.println("Database connection error: " + ex.getMessage());
		ex.printStackTrace();
	}
}
	public Boolean registerUser(UserModel user) {
		if (conn == null) {
		    System.out.println("Database connection is null.");
		    return false;
		} else {
		    System.out.println("Database connection is established.");
		}
		String insertQuery = "INSERT INTO User(Email,FullName,Gender,Address, ContactNo,DateOfBirth,YearsOfExperience,Qualification,Password,imageUrl) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement insertStmt = conn.prepareStatement(insertQuery)){
			insertStmt.setString(1, user.getEmail());
			insertStmt.setString(2, user.getFullName());
			insertStmt.setString(3, user.getGender());
			insertStmt.setString(4, user.getAddress());
			insertStmt.setString(5, user.getContactNo());
			insertStmt.setDate(6, Date.valueOf(user.getDateOfBirth()));
			insertStmt.setString(7, user.getQualification());
			insertStmt.setString(8, user.getYearsOfExperience());
			insertStmt.setString(9, user.getPassword());
			insertStmt.setString(10, user.getImageUrl());
			
			return insertStmt.executeUpdate() > 0;
		}
		 catch (SQLException e) {
				System.err.println("Error during user registration: " + e.getMessage());
				e.printStackTrace();
				return false;
			}
			
		}
	}