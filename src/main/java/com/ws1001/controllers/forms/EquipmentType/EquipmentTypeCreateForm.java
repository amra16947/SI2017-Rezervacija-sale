package com.ws1001.controllers.forms.EquipmentType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Dario on 5/22/2017.
 */
public class EquipmentTypeCreateForm {
    @Min(1)
    @NotNull
    private int label;

    @Size(min = 1, max = 255)
    @NotNull
    //@Pattern(message = "Invalid equipment type name!", regexp = "^[a-zA-Z0-9]*$")
    private String name;

    public int getLabel() {
        return label;
    }

    public void setLabel( int label ) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
