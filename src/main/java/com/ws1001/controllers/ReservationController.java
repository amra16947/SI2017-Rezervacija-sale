package com.ws1001.controllers;

import com.ws1001.controllers.forms.Reservation.ReservationCreateForm;
import com.ws1001.models.Classroom;
import com.ws1001.models.Reservation;
import com.ws1001.models.ScheduleBlock;
import com.ws1001.models.User;
import com.ws1001.services.ClassroomService;
import com.ws1001.services.ReservationService;
import com.ws1001.services.UserService;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class ReservationController extends BaseController<Reservation, ReservationService> {

    protected ClassroomService classroomService;
    protected UserService userService;

    @Autowired
    public void setUserService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/api/reservations/all")
    @ResponseBody
    @Override
    public Iterable<Reservation> all() {return super.all();}

    @GetMapping(path = "/api/reservations/today")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @ResponseBody
    public ResponseEntity allToday() {
        return ResponseEntity.ok(service.allToday());
    }

    @PostMapping(path = "/api/reservations")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid ReservationCreateForm newReservation) {
        try {
            Classroom classroom = classroomService.get(newReservation.getClassroomId());
            User user = userService.get(newReservation.getTeacherId());

            ScheduleBlock scheduleBlock = null; // Until we can figure out what it does
            Date reservedAt = newReservation.getReservedAt(); // Gets current day
            reservedAt.setMinutes(0);
            byte duration = newReservation.getDuration();

            Reservation reservation = new Reservation(classroom, user, scheduleBlock, reservedAt, duration);
            reservation = service.save(reservation);

            return ResponseEntity.ok(reservation);
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "/api/reservations/classroom/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity getByClassroomId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findByClassroom(id));
    }

    @DeleteMapping(path = "/api/reservations/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }
}
