package com.ws1001.controllers.forms.User;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserCreateForm {
    @Size(min = 4, max = 255) @NotNull
    private String firstName;

    @Size(min = 4, max = 255) @NotNull
    private String lastName;

    @Size(min = 4, max = 255) @NotNull
    private String username;

    @Size(min = 8, max = 255) @NotNull
    private String password;

    // For now we will presume we have only three roles: 0, 1, 2
    @Min(0) @Max(2) @NotNull
    private Integer type;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}