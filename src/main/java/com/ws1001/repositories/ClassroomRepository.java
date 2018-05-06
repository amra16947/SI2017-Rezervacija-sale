package com.ws1001.repositories;

import com.ws1001.models.Classroom;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClassroomRepository extends PagingAndSortingRepository<Classroom, Long> {
    Classroom findByName(String name);
}