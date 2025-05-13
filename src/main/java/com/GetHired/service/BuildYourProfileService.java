package com.GetHired.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GetHired.config.DbConfig;
import com.GetHired.model.UserModel;

public class BuildYourProfileService {
    private Connection conn;
    private boolean isConnectionError = false;

    public BuildYourProfileService() {
        try {
            this.conn = DbConfig.getDbConnection(); // ✅ initialize connection here
        } catch (Exception e) {
            isConnectionError = true;
            e.printStackTrace();
        }
    }

    public UserModel getUserDetails(String username) {
        if (isConnectionError) {
            System.out.println("Database connection error. Unable to fetch user details.");
            return null;
        }

        String query = "SELECT Email, FullName, Gender,Address, ContactNo, DateOfBirth,Qualification, YearsOfExperience,Password,imageUrl FROM user WHERE Email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                UserModel user = new UserModel();
                user.setEmail(result.getString("email"));
                user.setFullName(result.getString("FullName"));
                user.setGender(result.getString("Gender"));
                user.setAddress(result.getString("Address"));
                user.setContactNo(result.getString("ContactNo"));
                user.setDateOfBirth(result.getString("DateOfBirth"));
                user.setQualification(result.getString("Qualification"));      
                user.setYearsOfExperience(result.getString("YearsOfExperience"));
                user.setPassword(result.getString("Password"));
                user.setImageUrl(result.getString("imageUrl"));;

                return user;
            } else {
                System.out.println("No user details found for username: " + username);
            }
        } catch (SQLException e) {
            System.out.println("Database error while fetching user details: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
    public Boolean updateUser(UserModel user) {
        if (conn == null) {
            System.out.println("Database connection is null.");
            return false;
        } else {
            System.out.println("Database connection is established.");
        }

        String updateQuery = "UPDATE user SET email=?, FullName=?, Gender=?, Address=?, ContactNo=?, DateOfBirth=?, YearsOfExperience=?, Qualification=?, imageUrl=? WHERE email = ?";

        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            updateStmt.setString(1, user.getEmail());
            updateStmt.setString(2, user.getFullName());
            updateStmt.setString(3, user.getGender());
            updateStmt.setString(4, user.getAddress());
            updateStmt.setString(5, user.getContactNo());
            updateStmt.setDate(6, Date.valueOf(user.getDateOfBirth()));
            updateStmt.setString(7, user.getYearsOfExperience());
            updateStmt.setString(8, user.getQualification());
            updateStmt.setString(9, user.getImageUrl());
            updateStmt.setString(10, user.getEmail());

            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during user update: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

			
		}
