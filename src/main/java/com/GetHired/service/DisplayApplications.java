// com/GetHired/service/ApplicationViewService.java
package com.GetHired.service;

import com.GetHired.config.DbConfig;
import com.GetHired.model.ApplicationModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class  DisplayApplications{
    public List<ApplicationModel> getAllApplications() {
        List<ApplicationModel> apps = new ArrayList<>();
        String sql =
        	    "SELECT u.fullName, u.email, " +
        	    "j.JobId, j.JobTitle, j.JobLocation " +
        	    "FROM user_job uj " +
        	    "JOIN user u ON uj.Email = u.Email " +
        	    "JOIN Job j ON uj.JobId = j.JobId " +
        	    "ORDER BY j.JobTitle, u.fullName";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ApplicationModel m = new ApplicationModel();
                m.setUserId(rs.getString("Email"));
                m.setUserName(rs.getString("fullName"));
                m.setUserEmail(rs.getString("email"));
                m.setJobId(rs.getInt("JobId"));
                m.setJobTitle(rs.getString("JobTitle"));
                m.setJobLocation(rs.getString("JobLocation"));
                apps.add(m);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return apps;
    }
}
