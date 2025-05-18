package com.GetHired.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.GetHired.config.DbConfig;
import com.GetHired.model.Company;
import com.GetHired.model.jobModel;

public class DashboardService {
    private Connection conn;

    public DashboardService() {
        try {
            this.conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public int getJobsCount() {
        String query = "SELECT COUNT(*) FROM job";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Jobs count: " + count);
                return count;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching jobs count: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public jobModel getHighestSalaryJob() {
        String query = "SELECT j.JobTitle, j.JobSalary, c.CompanyName " +
                       "FROM job j JOIN company c ON j.JobCompanyId = c.CompanyId " +
                       "ORDER BY CAST(REGEXP_REPLACE(j.JobSalary, '[^0-9.]', '') AS DECIMAL) DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Company company = new Company(rs.getString("CompanyName"));
                jobModel job = new jobModel(
                    rs.getString("JobTitle"),
                    null, null, null, company,
                    rs.getString("JobSalary"),
                    null, null
                );
                System.out.println("Highest salary job: " + job.getJobTitle() + " (" + job.getJobSalary() + ")");
                return job;
            } else {
                System.out.println("No jobs found in getHighestSalaryJob");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching highest salary job: " + e.getMessage());
            e.printStackTrace();
            // Fallback query
            String fallbackQuery = "SELECT j.JobTitle, j.JobSalary, c.CompanyName " +
                                  "FROM job j JOIN company c ON j.JobCompanyId = c.CompanyId " +
                                  "LIMIT 1";
            try (PreparedStatement fallbackStmt = conn.prepareStatement(fallbackQuery);
                 ResultSet fallbackRs = fallbackStmt.executeQuery()) {
                if (fallbackRs.next()) {
                    Company company = new Company(fallbackRs.getString("CompanyName"));
                    jobModel job = new jobModel(
                        fallbackRs.getString("JobTitle"),
                        null, null, null, company,
                        fallbackRs.getString("JobSalary"),
                        null, null
                    );
                    System.out.println("Fallback job: " + job.getJobTitle() + " (" + job.getJobSalary() + ")");
                    return job;
                }
            } catch (SQLException fallbackE) {
                System.err.println("Error in fallback query: " + fallbackE.getMessage());
                fallbackE.printStackTrace();
            }
        }
        return null;
    }

    public Company getCompanyWithMostJobs() {
        String query = "SELECT c.CompanyName, COUNT(j.JobId) as jobCount " +
                       "FROM company c LEFT JOIN job j ON c.CompanyId = j.JobCompanyId " +
                       "GROUP BY c.CompanyId, c.CompanyName " +
                       "ORDER BY jobCount DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Company company = new Company(rs.getString("CompanyName"));
                company.setJobCount(rs.getInt("jobCount"));
                System.out.println("Top company: " + company.getCompanyName() + " (" + company.getJobCount() + " jobs)");
                return company;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching company with most jobs: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}