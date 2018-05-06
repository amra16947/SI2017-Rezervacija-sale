package com.ws1001.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Classroom extends BaseModel {
	public enum ClassroomType { Ordinary, Amphitheater, Laboratory, Office };
	public enum StatusType { InUse, Nonfunctional, WorkInProgess };

	private String name;
	private User teacherInCharge;
	private ClassroomType type;
	private StatusType status;
	private Integer seatCount;
	private Integer keyCount;
	private Integer takenKeyCount;
	private List<AccessGrant> accessGrants = new ArrayList<AccessGrant>();
	private List<ClassroomEquipment> equipment = new ArrayList<ClassroomEquipment>();
	private List<DamageReport> damageReports = new ArrayList<DamageReport>();
	private List<Reservation> reservations = new ArrayList<Reservation>();

	public Classroom() { }

	public Classroom(String name, Integer seatCount, Integer keyCount, StatusType status, ClassroomType type) {
		this.name = name;
		this.seatCount = seatCount;
		this.keyCount = keyCount;
		this.status = status;
		this.type = type;
		this.takenKeyCount = 0;
	}

	@Column(unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(optional = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	public User getTeacherInCharge() {
		return teacherInCharge;
	}

	public void setTeacherInCharge(User teacherInCharge) {
		this.teacherInCharge = teacherInCharge;
	}

	@Column(nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	public ClassroomType getType() {
		return type;
	}

	public void setType(ClassroomType type) {
		this.type = type;
	}

	@Column(nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	@Column(nullable = false)
	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	@Column(nullable = false)
	public Integer getKeyCount() {
		return keyCount;
	}

	public void setKeyCount(Integer keyCount) {
		this.keyCount = keyCount;
	}

	@Column(nullable = false)
	public Integer getTakenKeyCount() {
		return takenKeyCount;
	}

	public void setTakenKeyCount(Integer takenKeyCount) {
		this.takenKeyCount = takenKeyCount;
	}
	
	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<AccessGrant> getAccessGrants() {
		return accessGrants;
	}

	public void setAccessGrants(List<AccessGrant> accessGrants) {
		this.accessGrants = accessGrants;
	}
	
	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ClassroomEquipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<ClassroomEquipment> equipment) {
		this.equipment = equipment;
	}

	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	public List<DamageReport> getDamageReports() {
		return damageReports;
	}

	public void setDamageReports(List<DamageReport> damageReports) {
		this.damageReports = damageReports;
	}

	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Transient
	public void returnKey()
	{
		takenKeyCount--;
	}

	@Transient
	public void takeKey()
	{
		takenKeyCount++;
	}
}
