package com.user.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="user")
public class User{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long id;
	
	@Column(name="username",nullable=false)
	@Email
	private String username;
	
	@Column(name="password",nullable=false)
	@NotEmpty
	@Size(min=6)
	private String password;
	
	@Column(name="firstname",nullable=false)
	@NotEmpty
	private String firstname;
	
	@Column(name="lastname",nullable=false)
	@NotEmpty
	private String lastname;
	
	
	@Column(name="phone",nullable = false)
	@NotEmpty
	@Size(min=10,max=10)
	private String phone;
	
	@Column(name="role",nullable = false)
	@NotEmpty
	private String role;
	
	
	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}




	public User(long id, String username, String password, String firstname, String lastname, String phone,
			String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.role = role;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", phone=" + phone + ", role=" + role + "]";
	}
	
	
	
	
	
	
	

}
