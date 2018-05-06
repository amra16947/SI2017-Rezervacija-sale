package com.ws1001.controllers.forms.AccessGrant;

import javax.validation.constraints.NotNull;

/**
 * Created by amina on 22.05.2017..
 */
public class AccessGrantCreateForm {

    @NotNull
    private Long classroomId;

    @NotNull
    private Long userId;

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
