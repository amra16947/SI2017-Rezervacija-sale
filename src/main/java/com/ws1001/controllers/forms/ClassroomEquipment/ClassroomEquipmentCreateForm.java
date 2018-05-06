package com.ws1001.controllers.forms.ClassroomEquipment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Dario on 5/22/2017.
 */
public class ClassroomEquipmentCreateForm {

    @NotNull
    private Long classroomId;

    @NotNull
    private Long equipmentTypeId;

    @NotNull
    @Min(1)
    private int quantity;

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getEquipmentTypeId() {
        return equipmentTypeId;
    }

    public void setEquipmentTypeId(Long equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
