package com.ws1001.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class User extends BaseModel {
	public enum UserType { Teacher, Operator, Admin }

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private UserType type;
	private List<AccessGrant> accessGrants = new ArrayList<AccessGrant>();
	private List<Reservation> reservations = new ArrayList<Reservation>();
	private List<DamageReport> damageReports = new ArrayList<DamageReport>();

	public User() {

	}

	public User(String firstName, String lastName, String username, String password, UserType type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	@Column(nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(nullable = false)
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable = false)
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "teacher")
	public List<AccessGrant> getAccessGrants() {
		return accessGrants;
	}

	public void setAccessGrants(List<AccessGrant> accessGrants) {
		this.accessGrants = accessGrants;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "teacher")
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "reportedBy")
	public List<DamageReport> getDamageReports() {
		return damageReports;
	}

	public void setDamageReports(List<DamageReport> damageReports) {
		this.damageReports = damageReports;
	}

	@JsonIgnore
	@Transient
	public String getRole() {
		String result = null;
		switch (this.type){
			case Teacher:
				result = "ROLE_TEACHER";
				break;
			case Operator:
				result = "ROLE_OPERATOR";
				break;
			case Admin:
				result = "ROLE_ADMIN";
				break;
		}
		return result;
	}
}
