package com.ws1001.controllers.forms.Classroom;

import javax.validation.constraints.NotNull;

public class ClassroomExistsForm {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
