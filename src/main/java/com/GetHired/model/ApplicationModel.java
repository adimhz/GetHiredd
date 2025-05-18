// com/GetHired/model/ApplicationModel.java
package com.GetHired.model;

public class ApplicationModel {
    private int jobId;
    private String jobTitle;
    private String jobLocation;
    private String userId;
    private String userName;
    private String userEmail;

    // getters & setters
    public int getJobId() {
    	return jobId; 
    	}
    public void setJobId(int jobId)
    {
    	this.jobId = jobId; 
    	}

    public String getJobTitle() 
    { 
    	return jobTitle; 
    	}
    public void setJobTitle(String jobTitle) {
    	this.jobTitle = jobTitle; 
    	}

    public String getJobLocation() 
    { 
    	return jobLocation; 
    	}
    public void setJobLocation(String jobLocation) 
    { 
    	this.jobLocation = jobLocation;
    }

    public String getUserId() 
    {
    	return userId; 
    }
    public void setUserId(String userId) 
    {
    	this.userId = userId;
    }

    public String getUserName() 
    { 
    	return userName;
    	}
    public void setUserName(String userName)
    { 
    	this.userName = userName;
    }

    public String getUserEmail() { 
    	return userEmail; 
    	}
    public void setUserEmail(String userEmail) { 
    	this.userEmail = userEmail;
    	}
}

