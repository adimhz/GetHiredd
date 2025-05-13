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

public class ShowJobs {

    public List<jobModel> getAllJobs() {
        List<jobModel> jobs = new ArrayList<>();
        String query = "SELECT j.JobTitle, j.JobType, j.JobDeadline, j.JobQualification, j.JobSalary, j.JobLocation, j.JobDescription, c.CompanyName " +
                       "FROM Job j JOIN Company c ON j.JobCompanyId = c.CompanyId";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                jobModel job = new jobModel();
                job.setJobTitle(rs.getString("JobTitle"));
                job.setJobType(rs.getString("JobType"));
                job.setJobDeadline(rs.getString("JobDeadline"));
                job.setJobQualification(rs.getString("JobQualification"));
                job.setJobSalary(rs.getString("JobSalary"));
                job.setJobLocation(rs.getString("JobLocation"));
                job.setJobDescription(rs.getString("JobDescription"));
                job.setJobCompanyId(new Company(rs.getString("CompanyName")));
                jobs.add(job);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return jobs;
    }

    public List<jobModel> getFilteredJobs(String searchQuery, String sortBy) {
        List<jobModel> jobs = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT j.JobTitle, j.JobType, j.JobDeadline, j.JobQualification, j.JobSalary, j.JobLocation, j.JobDescription, c.CompanyName ")
             .append("FROM Job j JOIN Company c ON j.JobCompanyId = c.CompanyId WHERE 1=1 ");

        // Adding search filter if a search query is provided
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query.append("AND (j.JobTitle LIKE ? OR j.JobDescription LIKE ? OR j.JobLocation LIKE ?) ");
        }

        // Adding sorting options based on sortBy parameter
        if (sortBy != null && !sortBy.isEmpty()) {
            if (sortBy.equals("latest")) {
                query.append("ORDER BY j.JobDeadline DESC ");
            } else if (sortBy.equals("salary-high-low")) {
                query.append("ORDER BY j.JobSalary DESC ");
            } else if (sortBy.equals("salary-low-high")) {
                query.append("ORDER BY j.JobSalary ASC ");
            }
        }

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            // Setting the search parameters if searchQuery is provided
            if (searchQuery != null && !searchQuery.isEmpty()) {
                stmt.setString(1, "%" + searchQuery + "%");
                stmt.setString(2, "%" + searchQuery + "%");
                stmt.setString(3, "%" + searchQuery + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    jobModel job = new jobModel();
                    job.setJobTitle(rs.getString("JobTitle"));
                    job.setJobType(rs.getString("JobType"));
                    job.setJobDeadline(rs.getString("JobDeadline"));
                    job.setJobQualification(rs.getString("JobQualification"));
                    job.setJobSalary(rs.getString("JobSalary"));
                    job.setJobLocation(rs.getString("JobLocation"));
                    job.setJobDescription(rs.getString("JobDescription"));
                    job.setJobCompanyId(new Company(rs.getString("CompanyName")));
                    jobs.add(job);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return jobs;
    }
}
