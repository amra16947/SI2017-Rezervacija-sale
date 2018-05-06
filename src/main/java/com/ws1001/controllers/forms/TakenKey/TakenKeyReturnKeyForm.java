package com.ws1001.controllers.forms.TakenKey;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ramic on 24.05.2017..
 */
public class TakenKeyReturnKeyForm {
    @NotNull
    private Long reservationId;


    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservation) {
        this.reservationId = reservation;
    }

}
