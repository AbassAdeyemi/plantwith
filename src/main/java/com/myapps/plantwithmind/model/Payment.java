package com.myapps.plantwithmind.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;


@Entity
public class Payment {

	@Id
	@Column(name="payment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String amount;
	private boolean paid;
	private int paymentFlag=0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="livestock_id",nullable = false)
	private Livestock livestock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",nullable = false)
	private SiteUser user;
	
	private String paymentRef;
	
    @CreatedDate
	private Date creationDate;
		
	public Payment() {	
	}
	public Payment(String amount) {
		
		this.amount = amount;
	}
	
	public Payment(Long id, String amount, boolean paid, int paymentFlag,
			Livestock livestock, SiteUser user, String paymentRef,
			Date creationDate) {
		this.id = id;
		this.amount = amount;
		this.paid = paid;
		this.paymentFlag = paymentFlag;
		this.livestock = livestock;
		this.user = user;
		this.paymentRef = paymentRef;
		this.creationDate = creationDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	public int getPaymentFlag() {
		return paymentFlag;
	}
	public void setPaymentFlag(int paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Livestock getLivestock() {
		return livestock;
	}
	public void setLivestock(Livestock livestock) {
		this.livestock = livestock;
	}
	public SiteUser getUser() {
		return user;
	}
	public void setUser(SiteUser user) {
		this.user = user;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getPaymentRef() {
		return paymentRef;
	}
	public void setPaymentRef(String paymentRef) {
		this.paymentRef = paymentRef;
	}
	
	
	
	
	
	
}
