package com.GetHired.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.GetHired.config.DbConfig;
import com.GetHired.model.Company;
import com.GetHired.model.jobModel;

public class EditJobService {
    private Connection conn;

    public EditJobService() {
        try {
            this.conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public jobModel getJobDetailsByTitleAndCompany(String jobTitle, String companyName) {
        if (conn == null) {
            System.err.println("Database connection is null.");
            return null;
        }

        // Resolve companyName to CompanyId
        int companyId = getCompanyIdByName(companyName);
        if (companyId == -1) {
            System.err.println("No company found for name: " + companyName);
            return null;
        }

        String query = "SELECT JobTitle, JobType, JobDeadline, JobQualification, JobSalary, JobLocation, JobDescription " +
                      "FROM job WHERE JobTitle = ? AND JobCompanyId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, jobTitle);
            stmt.setInt(2, companyId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                jobModel job = new jobModel();
                job.setJobTitle(result.getString("JobTitle"));
                job.setJobType(result.getString("JobType"));
                job.setJobDeadline(result.getDate("JobDeadline") != null ? result.getDate("JobDeadline").toString() : null);
                job.setJobQualification(result.getString("JobQualification"));
                job.setJobCompanyId(new Company(companyName));
                job.setJobSalary(result.getString("JobSalary"));
                job.setJobLocation(result.getString("JobLocation"));
                job.setJobDescription(result.getString("JobDescription"));
                return job;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching job details: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateJob(jobModel job, String companyName) {
        if (conn == null) {
            System.err.println("Database connection is null.");
            return false;
        }

        // Resolve companyName to CompanyId
        int companyId = getCompanyIdByName(companyName);
        if (companyId == -1) {
            System.err.println("No company found for name: " + companyName);
            return false;
        }

        String updateQuery = "UPDATE job SET JobTitle=?, JobType=?, JobDeadline=?, JobQualification=?, JobSalary=?, JobLocation=?, JobDescription=? " +
                            "WHERE JobTitle = ? AND JobCompanyId = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            updateStmt.setString(1, job.getJobTitle());
            updateStmt.setString(2, job.getJobType());
            String deadline = job.getJobDeadline();
            if (deadline != null && !deadline.trim().isEmpty()) {
                updateStmt.setDate(3, java.sql.Date.valueOf(deadline));
            } else {
                updateStmt.setNull(3, java.sql.Types.DATE);
            }
            updateStmt.setString(4, job.getJobQualification());
            updateStmt.setString(5, job.getJobSalary());
            updateStmt.setString(6, job.getJobLocation());
            updateStmt.setString(7, job.getJobDescription());
            updateStmt.setString(8, job.getJobTitle());
            updateStmt.setInt(9, companyId);
            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during job update: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private int getCompanyIdByName(String companyName) {
        if (conn == null) {
            System.err.println("Database connection is null.");
            return -1;
        }

        String query = "SELECT CompanyId FROM company WHERE CompanyName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyName);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getInt("CompanyId");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching company ID: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        if (conn == null) {
            System.err.println("Database connection is null.");
            return companies;
        }

        String query = "SELECT CompanyName, CompanyLocation, CompanyContact FROM company";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
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
            System.err.println("Error fetching companies: " + e.getMessage());
            e.printStackTrace();
        }
        return companies;
    }
}