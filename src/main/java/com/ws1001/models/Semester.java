package com.ws1001.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Semester extends BaseModel {
	private Date beginsAt;
	private Date endsAt;
	private List<ScheduleBlock> scheduleBlocks = new ArrayList<ScheduleBlock>();

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getBeginsAt() {
		return beginsAt;
	}

	public void setBeginsAt(Date beginsAt) {
		this.beginsAt = beginsAt;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(Date endsAt) {
		this.endsAt = endsAt;
	}
	
	@OneToMany(mappedBy = "semester")
	public List<ScheduleBlock> getScheduleBlocks() {
		return scheduleBlocks;
	}

	public void setScheduleBlocks(List<ScheduleBlock> scheduleBlocks) {
		this.scheduleBlocks = scheduleBlocks;
	}
}
