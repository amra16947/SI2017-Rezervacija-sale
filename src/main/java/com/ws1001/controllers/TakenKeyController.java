package com.ws1001.controllers;

import com.ws1001.controllers.forms.TakenKey.TakenKeyReturnKeyForm;
import com.ws1001.controllers.forms.TakenKey.TakenKeyTakeForm;
import com.ws1001.models.Classroom;
import com.ws1001.models.Reservation;
import com.ws1001.models.TakenKey;
import com.ws1001.models.User;
import com.ws1001.services.ClassroomService;
import com.ws1001.services.ReservationService;
import com.ws1001.services.TakenKeyService;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by ramic on 22.05.2017..
 */
@RestController
public class TakenKeyController extends BaseController<TakenKey, TakenKeyService> {

    protected ReservationService reservationService;

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    protected ClassroomService classroomService;

    @Autowired
    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping(path = "/api/taken-keys/all")
    @ResponseBody
    @Override
    public Iterable<TakenKey> all() {
        return super.all();
    }


    @PostMapping(path = "/api/taken-keys/take")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @ResponseBody
    public ResponseEntity take(@RequestBody @Valid TakenKeyTakeForm newTakenKey) {
        try {
            Long reservationId = newTakenKey.getReservationId();
            Reservation currentResrevation  = reservationService.get(reservationId);
            if (currentResrevation == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a reservation");
            }

            Long currentClassroomId = currentResrevation.getClassroom().getId();
            Classroom currentClassroom = classroomService.get(currentClassroomId);

            if(currentClassroom.getTakenKeyCount() == currentClassroom.getKeyCount()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No more keys");
            }

            currentClassroom.takeKey();
            currentClassroom = classroomService.save(currentClassroom);

            TakenKey takenKey = new TakenKey(currentResrevation,
                    new Date(),
                    null);

            //takenKey = service.save(takenKey);
            return ResponseEntity.ok(takenKey);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "/api/taken-keys/return")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @ResponseBody
    public ResponseEntity returnKey(@RequestBody @Valid TakenKeyReturnKeyForm returningKey) {
        try {
            Long reservationId = returningKey.getReservationId();
            Reservation currentResrevation = reservationService.get(reservationId);
            if (currentResrevation == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a reservation");
            }

            Long currentClassroomId = currentResrevation.getClassroom().getId();
            Classroom currentClassroom = classroomService.get(currentClassroomId);

            if(currentClassroom.getTakenKeyCount() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No keys to return");
            }
            currentClassroom.returnKey();
            currentClassroom = classroomService.save(currentClassroom);

            TakenKey returnedKey = new TakenKey(currentResrevation,null, new Date());
            //returnedKey = service.save(returnedKey);
            return ResponseEntity.ok(returnedKey);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "/api/taken-keys/allTaken")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @ResponseBody
    public ResponseEntity allTaken() throws ServiceException {
        return  ResponseEntity.ok(service.takenKeys());
    }

}
