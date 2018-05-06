package com.ws1001.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class TakenKey extends BaseModel {
	private Reservation reservation;
	private Date takenAt;
	private Date returnedAt;

	public TakenKey(){

	}
	public TakenKey(Reservation reservation, Date takenAt, Date returnedAt){
		this.reservation = reservation;
		this.takenAt = takenAt;
		this.returnedAt = returnedAt;
	}

	public void returnKey(Date returnedAt){
		this.returnedAt = returnedAt;
	}

	@OneToOne(optional = false)
	@JsonIdentityReference(alwaysAsId = true)
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getTakenAt() {
		return takenAt;
	}

	public void setTakenAt(Date takenAt) {
		this.takenAt = takenAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getReturnedAt() {
		return returnedAt;
	}

	public void setReturnedAt(Date returnedAt) {
		this.returnedAt = returnedAt;
	}
}
