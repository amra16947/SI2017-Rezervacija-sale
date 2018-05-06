package com.ws1001.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class ScheduleBlock extends BaseModel {
	private Semester semester;
	private List<Reservation> reservations = new ArrayList<Reservation>();

	@ManyToOne(optional = false)
	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	@OneToMany(mappedBy = "scheduleBlock")
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
}
