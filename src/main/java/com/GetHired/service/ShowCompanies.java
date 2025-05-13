package com.GetHired.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.GetHired.config.DbConfig;
import com.GetHired.model.Company;

public class ShowCompanies {
    private Connection conn;

    public ShowCompanies() {
        try {
            this.conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Fetch all companies from the database
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        if (conn == null) {
            System.err.println("Database connection is null.");
            return companies;
        }

        String query = "SELECT CompanyName, CompanyLocation, CompanyContact FROM company";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Company company = new Company(
                    rs.getString("CompanyName"),
                    rs.getString("CompanyLocation"),
                    rs.getString("CompanyContact")
                );
                companies.add(company);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching companies: " + e.getMessage());
            e.printStackTrace();
        }
        return companies;
    }

    // Fetch companies filtered by search query
    public List<Company> getFilteredCompanies(String searchQuery) {
        List<Company> companies = new ArrayList<>();
        if (conn == null) {
            System.err.println("Database connection is null.");
            return companies;
        }

        String query = "SELECT CompanyName, CompanyLocation, CompanyContact FROM company " +
                       "WHERE CompanyName LIKE ? OR CompanyLocation LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            String searchPattern = "%" + searchQuery + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Company company = new Company(
                    rs.getString("CompanyName"),
                    rs.getString("CompanyLocation"),
                    rs.getString("CompanyContact")
                );
                companies.add(company);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching filtered companies: " + e.getMessage());
            e.printStackTrace();
        }
        return companies;
    }
}