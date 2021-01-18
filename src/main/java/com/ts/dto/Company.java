package com.ts.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.ts.dto.Review;

@XmlRootElement
@Entity
public class Company {
	@Id
	@GeneratedValue
	private int companyId;
	private String companyName;
	private String companyQuestions;
	
	@OneToMany(mappedBy="company",fetch = FetchType.LAZY)
	private Set<Review> reviewList =new HashSet<Review>();
	
	public Company(int companyId, String companyName, String companyQuestions) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyQuestions = companyQuestions;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyQuestions() {
		return companyQuestions;
	}

	public void setCompanyQuestions(String companyQuestions) {
		this.companyQuestions = companyQuestions;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyQuestions="
				+ companyQuestions + "]";
	}
	
	
	
}
