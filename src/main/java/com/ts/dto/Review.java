package com.ts.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ts.dto.Company;

public class Review {
	private int reviewId;
	
	@ManyToOne
	@JoinColumn(name="companyId")
	
	private Company company;

}
