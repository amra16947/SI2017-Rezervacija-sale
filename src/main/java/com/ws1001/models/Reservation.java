package com.ws1001.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Reservation extends BaseModel {
	private Classroom classroom;
	private User teacher;
	private ScheduleBlock scheduleBlock;
	private Date reservedAt;
	private byte duration;

	public Reservation() {

	}

	public Reservation(Classroom classroom, User teacher, ScheduleBlock scheduleBlock,
					   Date reservedAt, byte duration) {
		this.classroom = classroom;
		this.teacher = teacher;
		this.scheduleBlock = scheduleBlock;
		this.reservedAt = reservedAt;
		this.duration = duration;
	}

	@ManyToOne(optional = false)
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@ManyToOne(optional = false)
	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	
	@ManyToOne(optional = true)
	public ScheduleBlock getScheduleBlock() {
		return scheduleBlock;
	}

	public void setScheduleBlock(ScheduleBlock scheduleBlock) {
		this.scheduleBlock = scheduleBlock;
	}

	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="Europe/Sarajevo")
	public Date getReservedAt() {
		return reservedAt;
	}

	public void setReservedAt(Date reservedAt) {
		this.reservedAt = reservedAt;
	}

	@Column(nullable = false)
	public byte getDuration() {
		return duration;
	}

	public void setDuration(byte duration) {
		this.duration = duration;
	}
}
