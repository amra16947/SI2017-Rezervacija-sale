package com.ws1001.controllers.forms.Reservation;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ws1001.models.ScheduleBlock;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

public class ReservationCreateForm {
    @NotNull
    private Long classroomId;

    @NotNull
    private Long teacherId;

    private ScheduleBlock scheduleBlock;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="Europe/Sarajevo")
    private Date reservedAt;

    public Date getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(Date reservedAt) {
        this.reservedAt = reservedAt;
    }

    @Min(1) @NotNull
    private byte duration;

    public ScheduleBlock getScheduleBlock() {
        return scheduleBlock;
    }

    public void setScheduleBlock(ScheduleBlock scheduleBlock) {
        this.scheduleBlock = scheduleBlock;
    }

    public byte getDuration() {
        return duration;
    }

    public void setDuration(byte duration) {
        this.duration = duration;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
