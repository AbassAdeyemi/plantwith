package com.myapps.plantwithmind.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="livestock")
public class Livestock {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String name;
	
	private Date startDate;
	
	private Date endDate;
	
	@OneToOne(targetEntity=SiteUser.class)
	@JoinColumn(name="user_id",nullable = true)
	private SiteUser user;
	
	@Lob
	private String description;
	

	private String firstPay;
	
	
	private String secondPay;
	
	
	private String thirdPay;
	
	private int payInterval;
	
	@Column(name="photo_directory",length=10)
	private String photoDirectory;
	
	@Column(name="photo_name",length=10)
	private String photoName;
	
	@Column(name="photo_extension",length=5)
	private String photoExtension;
	
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

	public int getPayInterval() {
		return payInterval;
	}

	public void setPayInterval(int payInterval) {
		this.payInterval = payInterval;
	}

	public SiteUser getUser() {
		return user;
	}

	public void setUser(SiteUser user) {
		this.user = user;
	}

	public String getPhotoDirectory() {
		return photoDirectory;
	}

	public void setPhotoDirectory(String photoDirectory) {
		this.photoDirectory = photoDirectory;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoExtension() {
		return photoExtension;
	}

	public void setPhotoDetails(FileInfo info){
		photoDirectory= info.getSubDirectory();
		photoName= info.getBasename();
		photoExtension= info.getExtension();
	}
	
	public Path getPhoto(String baseDirectory) {
		if(photoName == null) {
			return null;
		}
		
		return Paths.get(baseDirectory, photoDirectory, photoName + "." +  photoExtension);
	}
	
	public void setPhotoExtension(String photoExtension) {
		this.photoExtension = photoExtension;
	}
	
	
	
	
	
	
	
	
	
	
}
