package com.GetHired.model;

public class Company {
	private String CompanyName;
	private String CompanyLocation;
	private String CompanyContact;
    private int jobCount;
    
	public Company( String companyName, String companyLocation, String companyContact) {	
		CompanyName = companyName;
		CompanyLocation = companyLocation;
		CompanyContact = companyContact;
	}
	
	public Company(String company) {
		CompanyName=company;
	}

	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getCompanyLocation() {
		return CompanyLocation;
	}
	public void setCompanyLocation(String companyLocation) {
		CompanyLocation = companyLocation;
	}
	public String getCompanyContact() {
		return CompanyContact;
	}
	public void setCompanyContact(String companyContact) {
		CompanyContact = companyContact;
	}
	    public int getJobCount() {
	        return jobCount;
	    }

	    public void setJobCount(int jobCount) {
	        this.jobCount = jobCount;
	    }
	}
