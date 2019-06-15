package com.myapps.plantwithmind.model;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="crop")
public class Crop {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="crop_id")
	private Long id;
	
	@NotBlank
	private String name;
	
	private String description;
	
	@Lob
	@Column(name = "image", columnDefinition="BLOB")
	private Byte[] image;
	
	private int periodOfGrowth;
	private BigDecimal costOfPesticide;
	private BigDecimal costOfHerbicide;
	private BigDecimal costOfLandPreparation;
	private BigDecimal costOfFertilizer;
	private String firstPay;
	private String secondPay;
	private String thirdPay;
	
	public Crop(){
		
	}
	
	public Crop(String name) {
		this.name = name;
	}



	public int getPeriodOfGrowth(){
		return periodOfGrowth;
	}
	public void setPeriodOfGrowth(int periodOfGrowth){
		this.periodOfGrowth = periodOfGrowth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Byte[] getImage() {
		return image;
	}
	public void setImage(Byte[] image) {
		this.image = image;
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCostOfPesticide() {
		return costOfPesticide;
	}


	public void setCostOfPesticide(BigDecimal costOfPesticide) {
		this.costOfPesticide = costOfPesticide;
	}


	public BigDecimal getCostOfHerbicide() {
		return costOfHerbicide;
	}


	public void setCostOfHerbicide(BigDecimal costOfHerbicide) {
		this.costOfHerbicide = costOfHerbicide;
	}


	public BigDecimal getCostOfLandPreparation() {
		return costOfLandPreparation;
	}


	public void setCostOfLandPreparation(BigDecimal costOfLandPreparation) {
		this.costOfLandPreparation = costOfLandPreparation;
	}


	public BigDecimal getCostOfFertilizer() {
		return costOfFertilizer;
	}


	public void setCostOfFertilizer(BigDecimal costOfFertilizer) {
		this.costOfFertilizer = costOfFertilizer;
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

	@Override
	public String toString() {
		return "Crop [id=" + id + ", name=" + name + ", description="
				+ description + ", image=" + Arrays.toString(image)
				+ ", periodOfGrowth=" + periodOfGrowth + ", costOfPesticide="
				+ costOfPesticide + ", costOfHerbicide=" + costOfHerbicide
				+ ", costOfLandPreparation=" + costOfLandPreparation
				+ ", costOfFertilizer=" + costOfFertilizer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime
				* result
				+ ((costOfFertilizer == null) ? 0 : costOfFertilizer.hashCode());
		result = prime * result
				+ ((costOfHerbicide == null) ? 0 : costOfHerbicide.hashCode());
		result = prime
				* result
				+ ((costOfLandPreparation == null) ? 0 : costOfLandPreparation
						.hashCode());
		result = prime * result
				+ ((costOfPesticide == null) ? 0 : costOfPesticide.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + periodOfGrowth;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crop other = (Crop) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (costOfFertilizer == null) {
			if (other.costOfFertilizer != null)
				return false;
		} else if (!costOfFertilizer.equals(other.costOfFertilizer))
			return false;
		if (costOfHerbicide == null) {
			if (other.costOfHerbicide != null)
				return false;
		} else if (!costOfHerbicide.equals(other.costOfHerbicide))
			return false;
		if (costOfLandPreparation == null) {
			if (other.costOfLandPreparation != null)
				return false;
		} else if (!costOfLandPreparation.equals(other.costOfLandPreparation))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (periodOfGrowth != other.periodOfGrowth)
			return false;
		return true;
	}


	
	
	
	
}
