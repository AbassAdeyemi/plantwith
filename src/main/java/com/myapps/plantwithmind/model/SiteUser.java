package com.myapps.plantwithmind.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Table(name="user")
public class SiteUser {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="firstname")
	@NotBlank(message="Firstname cannot be empty")
	private String firstname;
	
	@Column(name="lastname")
	@NotBlank(message="Lastname cannot be empty")
	private String lastname;
	
	@Column(name="email", unique=true)
	@Email(message="{register.email.invalid}")
	@NotBlank(message="{register.email.invalid}")
	private String email;
	
	@Column(name="password", length=60)
	private String password;
	
	@Transient
	@Size(min=5,max=15,message="{register.password.size}")
	private String plainPassword;
	
	@Transient
	private String repeatPassword;
	
	@Column(name="address")
	@NotBlank(message="Address cannot be empty")
	private String address;
	
	@Column(name="role")
	private String role;
	
	@Column(name="phone")
	@NotBlank(message="Phone number cannot be empty")
	private String phonenumber;
	
	@Column(name="state")
	@NotBlank(message="State cannot be empty")
	private String state;
	
	@Column(name="lga")
	@NotBlank(message="LGA cannot be empty")
	private String lga;
	
	private Boolean enabled;
	
	public SiteUser(Long id,String firstname, String lastname, String email,
			String password, String address,String role,String repeatPassword,
			String state,String lga,String plainPassword) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.id = id;
		this.role = role;
		this.repeatPassword = repeatPassword;
		this.setPlainPassword(password);
		
		
	}
	public SiteUser() {
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.password = new BCryptPasswordEncoder().encode(plainPassword);
		this.plainPassword = plainPassword;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLga() {
		return lga;
	}
	public void setLga(String lga) {
		this.lga = lga;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	@Override
	public String toString() {
		return "SiteUser [id=" + id + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", plainPassword=" + plainPassword
				+ ", repeatPassword=" + repeatPassword + ", address=" + address
				+ ", role=" + role + ", phonenumber=" + phonenumber
				+ ", state=" + state + ", lga=" + lga + ", enabled=" + enabled
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
}
