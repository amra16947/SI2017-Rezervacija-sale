package com.ws1001.services;

import com.ws1001.models.Reservation;
import com.ws1001.repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import com.ws1001.services.exceptions.ServiceException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService extends BaseService<Reservation, ReservationRepository> {
    public List<Reservation> findByTeacher(Long teacherId) {
        return repository.findAllByTeacherId(teacherId);
    }

    public List<Reservation> findByClassroom(Long classroomId) {
        return repository.findAllByClassroomId(classroomId);
    }

    public Reservation save(Reservation reservation) throws ServiceException {
        if (!reservationValid(reservation)) {
            throw new ServiceException("Invalid reservation time!");
        }

        Reservation conflict = findReservationConflict(reservation);

        if (conflict != null) {
            throw new ServiceException(String.format("User %s reserved that classroom for %d:00",
                    (conflict.getTeacher().getFirstName() + " " + conflict.getTeacher().getLastName()),
                    conflict.getReservedAt().getHours()));
        }

        if (!(teacherHasRightsToClassroom(reservation))) {
            throw new ServiceException("Teacher not allowed to use classroom!");
        }

        return super.save(reservation);
    }

    public List<Reservation> allToday() {
        Date d1 = new Date(); d1.setHours(0); d1.setMinutes(0); d1.setSeconds(0);
        Date d2 = new Date(); d2.setHours(23); d2.setMinutes(59); d2.setSeconds(0);

        return repository.findAllByReservedAtBetween(d1, d2);
    }

    private boolean reservationValid(Reservation reservation) {
        int hour = reservation.getReservedAt().getHours();
        int duration = reservation.getDuration();

        return hour >= 7 && hour + duration <= 22;
    }

    private Reservation findReservationConflict(Reservation reservation) {
        int startHour = reservation.getReservedAt().getHours();
        byte duration = reservation.getDuration();

        List<Reservation> classroomReservations = this.findByClassroom(reservation.getClassroom().getId());

        for (Reservation classroomReservation : classroomReservations) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            if(fmt.format(reservation.getReservedAt()).equals(fmt.format(classroomReservation.getReservedAt()))) {
                int crStartHour = classroomReservation.getReservedAt().getHours();
                int crDuration = classroomReservation.getDuration();

                if ( (startHour < crStartHour && startHour + duration > crStartHour) || startHour == crStartHour) {
                    return classroomReservation;
                }
            }
        }

        return null;
    }

    private boolean teacherHasRightsToClassroom(Reservation reservation) {
        // return new ClassroomService().availableTo(reservation.getClassroom(), reservation.getUser());
        return true; // TODO: Fix when (and if) method is implemented
    }
}
