package com.GetHired.model;

public class jobModel {
    private int jobId;
    private String JobTitle;
    private String JobType;
    private String JobDeadline;
    private String JobQualification;
    private Company JobCompanyId;
    private String JobSalary;
    private String JobLocation;
    private String JobDescription;
    private boolean hasApplied;
    
    

    public jobModel(int jobId, String jobTitle, String jobType, String jobDeadline, String jobQualification,
			Company jobCompanyId, String jobSalary, String jobLocation, String jobDescription, boolean hasApplied) {
		super();
		this.jobId = jobId;
		JobTitle = jobTitle;
		JobType = jobType;
		JobDeadline = jobDeadline;
		JobQualification = jobQualification;
		JobCompanyId = jobCompanyId;
		JobSalary = jobSalary;
		JobLocation = jobLocation;
		JobDescription = jobDescription;
		this.hasApplied = hasApplied;
	}

	public jobModel(String jobTitle, String jobType, String jobDeadline, String jobQualification, Company jobCompanyId,
            String jobSalary, String jobLocation, String jobDescription) {
        super();
        JobTitle = jobTitle;
        JobType = jobType;
        JobDeadline = jobDeadline;
        JobQualification = jobQualification;
        JobCompanyId = jobCompanyId;
        JobSalary = jobSalary;
        JobLocation = jobLocation;
        JobDescription = jobDescription;
    }
    
    public jobModel() {
        super();
    }
    public String getJobType() {
        return JobType;
    }
    public void setJobType(String jobType) {
        JobType = jobType;
    }
    public String getJobTitle() {
        return JobTitle;
    }
    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }
    public String getJobDeadline() {
        return JobDeadline;
    }
    public void setJobDeadline(String jobDeadline) {
        JobDeadline = jobDeadline;
    }
    public String getJobQualification() {
        return JobQualification;
    }
    public void setJobQualification(String jobQualification) {
        JobQualification = jobQualification;
    }
    public Company getJobCompanyId() {
        return JobCompanyId;
    }
    public void setJobCompanyId(Company jobCompanyId) {
        JobCompanyId = jobCompanyId;
    }
    public String getJobSalary() {
        return JobSalary;
    }
    public void setJobSalary(String jobSalary) {
        JobSalary = jobSalary;
    }
    public String getJobLocation() {
        return JobLocation;
    }
    public void setJobLocation(String jobLocation) {
        JobLocation = jobLocation;
    }
    public String getJobDescription() {
        return JobDescription;
    }
    public void setJobDescription(String jobDescription) {
        JobDescription = jobDescription;
    }
    public int getJobId() {
        return jobId;
    }
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    public boolean isHasApplied() {
        return hasApplied;
    }
    public void setHasApplied(boolean hasApplied) {
        this.hasApplied = hasApplied;
    }
}