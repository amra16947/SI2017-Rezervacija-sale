package com.ws1001.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class DamageReport extends BaseModel {
	private Classroom classroom;
	private User reportedBy;
	private Reservation reservation;
	private String description;
	private Date reportedAt;
	private boolean repotedSubsequently;

	@ManyToOne(optional = false)
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	@ManyToOne
	public User getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(User reportedBy) {
		this.reportedBy = reportedBy;
	}
	
	@OneToOne
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Column(length = 2048, nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getReportedAt() {
		return reportedAt;
	}

	public void setReportedAt(Date reportedAt) {
		this.reportedAt = reportedAt;
	}

	@Column(nullable = false)
	public boolean wasRepotedSubsequently() {
		return repotedSubsequently;
	}

	public void setRepotedSubsequently(boolean repotedSubsequently) {
		this.repotedSubsequently = repotedSubsequently;
	}
}
