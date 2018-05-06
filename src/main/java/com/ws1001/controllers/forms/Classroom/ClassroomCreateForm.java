package com.ws1001.controllers.forms.Classroom;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClassroomCreateForm {
    @Size(min = 1, max = 255) @NotNull
    private String name;

    @Min(1) @NotNull
    private Integer seatCount;

    @Min(1) @NotNull
    private Integer keyCount;

    private Long teacherId;

    // InUse, Nonfunctional, WorkInProgess
    @Min(0) @Max(2) @NotNull
    private Integer status;

    // Ordinary, Amphitheater, Laboratory, Office
    @Min(0) @Max(3) @NotNull
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(Integer keyCount) {
        this.keyCount = keyCount;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
