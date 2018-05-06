package com.ws1001.repositories;


import com.ws1001.models.Classroom;
import com.ws1001.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.ws1001.models.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {
    List<Reservation> findAllByTeacherId(Long teacherId);
    List<Reservation> findAllByClassroomId(Long classroomId);
    List<Reservation> findAllByReservedAtBetween(Date start, Date end);
}