package com.GetHired.model;

public class UserModel {
	private String Email;
	private String FullName;
	private String Gender;
	private String Address;
	private String ContactNo;
	private String DateOfBirth;
	private String YearsOfExperience;
	private String Qualification;
	private String Password;
	private String imageUrl;
	
	public UserModel(String email, String fullName, String gender, String address, String contactNo, String dateOfBirth,
			String yearsOfExperience, String qualification, String password, String ImageUrl) {
		super();
		Email = email;
		FullName = fullName;
		Gender = gender;
		Address = address;
		ContactNo = contactNo;
		DateOfBirth = dateOfBirth;
		YearsOfExperience = yearsOfExperience;
		Qualification = qualification;
		Password = password;
		imageUrl = ImageUrl;
	}


	
	public UserModel(String email, String fullName, String gender, String address, String contactNo, String dateOfBirth,
			String yearsOfExperience, String qualification, String ImageUrl) {
		super();
		Email = email;
		FullName = fullName;
		Gender = gender;
		Address = address;
		ContactNo = contactNo;
		DateOfBirth = dateOfBirth;
		YearsOfExperience = yearsOfExperience;
		Qualification = qualification;
		imageUrl = ImageUrl;
	}




	public UserModel(String email, String password) {
		super();
		Email = email;
		Password = password;
	}

	public UserModel() {
		super();
	}



	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getContactNo() {
		return ContactNo;
	}
	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getYearsOfExperience() {
		return YearsOfExperience;
	}
	public void setYearsOfExperience(String yearsOfExperience) {
		YearsOfExperience = yearsOfExperience;
	}
	public String getQualification() {
		return Qualification;
	}
	public void setQualification(String qualification) {
		Qualification = qualification;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
	