package com.ts.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="Senior")
public class Senior {
	@Id
	@GeneratedValue
	public int seniorId;
	private String firstName;
	private String lastName;
	private String phone;
	private String emailId;
	private String branch;
	private String password;
	private int year;
	private String placedStatus;
	
	public Senior() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Senior(int seniorId, String firstName, String lastName, String phone, String emailId, String password, String branch,
			int year, String placedStatus) {
		super();
		this.seniorId = seniorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.emailId = emailId;
		this.branch = branch;
		this.year = year;
		this.placedStatus = placedStatus;
	}



	public int getSeniorId() {
		return seniorId;
	}


	public void setSeniorId(int seniorId) {
		this.seniorId = seniorId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBranch() {
		return branch;
	}


	public void setBranch(String branch) {
		this.branch = branch;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getPlacedStatus() {
		return placedStatus;
	}


	public void setPlacedStatus(String placedStatus) {
		this.placedStatus = placedStatus;
	}



	@Override
	public String toString() {
		return "Senior [seniorId=" + seniorId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone="
				+ phone + ", emailId=" + emailId + ", branch=" + branch + ", password=" + password + ", year=" + year
				+ ", placedStatus=" + placedStatus + "]";
	}
	

}


