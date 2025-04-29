package com.GetHired.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GetHired.config.DbConfig;
import com.GetHired.model.UserModel;
import com.GetHired.util.PasswordUtil;

public class loginService {
    private Connection conn;
    private boolean isConnectionError = false;

    public loginService() {
        try {
            this.conn = DbConfig.getDbConnection(); // ✅ initialize connection here
        } catch (Exception e) {
            isConnectionError = true;
            e.printStackTrace();
        }
    }

    public Boolean loginUser(UserModel user) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT * FROM user WHERE Email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getEmail());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return validatePassword(rs, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return false;
    }

    private boolean validatePassword(ResultSet result, UserModel user) throws SQLException {
        String dbPassword = result.getString("Password");
        String dbEmail = result.getString("Email");

        String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbEmail);

        return decryptedPassword.equals(user.getPassword());
    }
    public UserModel getUserDetails(String username) {
        if (isConnectionError) {
            System.out.println("Database connection error. Unable to fetch user details.");
            return null;
        }

        String query = "SELECT Email, FullName, Gender, ContactNo, DateOfBirth,Qualification, YearsOfExperience,Password,imageUrl FROM user WHERE Email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                UserModel user = new UserModel();
                user.setEmail(result.getString("email"));
                user.setFullName(result.getString("FullName"));
                user.setGender(result.getString("Gender"));
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

}
