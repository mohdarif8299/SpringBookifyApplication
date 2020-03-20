package com.com.books.bookify.models;

import java.io.File;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="UsersDetails")
@EntityListeners(AuditingEntityListener.class)
public class UserDetails {
	@Id
    @Basic(optional = false)
    @Column(name = "username",unique=true, nullable = false)
	@JsonProperty("username")
	private String username;
	@NotNull
	@JsonProperty("name")
	private String name;
	@NotNull
	@JsonProperty("password")
	private String password;
	@JsonProperty("number")
	@Nullable
	private String number;
	@JsonProperty("image")
	private String image;
	
	public String getNumber() {
		return number;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String string) {
		this.image = string;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", name=" + name + ", password=" + password + ", number=" + number
				+ "]";
	}
	
	public UserDetails(String username, @NotNull String name, @NotNull String password, String number, String image) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.number = number;
		this.image = image;
	}
	public UserDetails() {
		super();
	}
	
}
