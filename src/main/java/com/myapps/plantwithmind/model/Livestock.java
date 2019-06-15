package com.myapps.plantwithmind.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Livestock {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private Date startDate;
	
	private Date endDate;
	
	@OneToOne(targetEntity=SiteUser.class)
	@JoinColumn(name="user_id",nullable = true)
	private SiteUser user;
	
	@Lob
	@Column(name = "image", columnDefinition="BLOB")
	private Byte[] image;
	
	@Lob
	private String description;
	
	private String firstPay;
	
	private String secondPay;
	
	private String thirdPay;
	
	private String periodOfGrowth;
	
       public Livestock(){
		
	}
	
	public Livestock(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstPay() {
		return firstPay;
	}

	public void setFirstPay(String firstPay) {
		this.firstPay = firstPay;
	}

	public String getSecondPay() {
		return secondPay;
	}

	public void setSecondPay(String secondPay) {
		this.secondPay = secondPay;
	}

	public String getThirdPay() {
		return thirdPay;
	}

	public void setThirdPay(String thirdPay) {
		this.thirdPay = thirdPay;
	}

	public String getPeriodOfGrowth() {
		return periodOfGrowth;
	}

	public void setPeriodOfGrowth(String periodOfGrowth) {
		this.periodOfGrowth = periodOfGrowth;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public SiteUser getUser() {
		return user;
	}

	public void setUser(SiteUser user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
}
