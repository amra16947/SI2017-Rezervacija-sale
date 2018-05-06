package com.ws1001.repositories;

import com.ws1001.models.AccessGrant;
import com.ws1001.models.Classroom;
import com.ws1001.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by amina on 22.05.2017..
 */
public interface AccessGrantRepository extends PagingAndSortingRepository<AccessGrant, Long> {
    AccessGrant findByClassroomIdAndTeacherId(Long classroomId, Long teacherId);
    List <AccessGrant> findAllByClassroomId(Long classroomId);
    List <AccessGrant> findAllByTeacherId(Long teacherId);
}
