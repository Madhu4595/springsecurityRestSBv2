package com.example.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;


@Entity
@Table(	name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 120)
	private String password;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch= FetchType.EAGER)
	@JoinTable(name="user_roles",
	joinColumns = {@JoinColumn(name="fk_user_id",referencedColumnName = "user_id")},
	inverseJoinColumns= {@JoinColumn(name="fk_role_id",referencedColumnName = "role_id")}
	)
	
	private Set<Role> roles=new HashSet<>();
	

	public User() {
	}

	public String getPassword() {
		return password;
	}

	

	
}