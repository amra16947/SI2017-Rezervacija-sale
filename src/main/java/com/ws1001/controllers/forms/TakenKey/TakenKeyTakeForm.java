package com.ws1001.controllers.forms.TakenKey;

import com.ws1001.models.Reservation;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ramic on 22.05.2017..
 */
public class TakenKeyTakeForm {
    @NotNull
    private Long reservationId;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservation) {
        this.reservationId = reservation;
    }
}
