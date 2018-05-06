package com.ws1001.services;

import com.ws1001.models.AccessGrant;
import com.ws1001.models.Classroom;
import com.ws1001.repositories.AccessGrantRepository;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amina on 22.05.2017..
 */

@Service
public class AccessGrantService extends BaseService<AccessGrant, AccessGrantRepository> {
    @Autowired
    private JpaContext jpaContext;

    public AccessGrant save(AccessGrant model) throws ServiceException {
        if(model.getId() == null && getByClassroomIdAndTeacherId(model.getClassroom().getId(), model.getTeacher().getId()) != null)
          throw new ServiceException("An access grant with these parameters already exists!");
        else if(model.getTeacher().getRole()!= "ROLE_TEACHER")
            throw new ServiceException("Added user is not a teacher!");
        else
        if(model.getId() != null) {
            // TO-DO: Finish proper partial update logic (shouldn't send the whole object during update)
        }

        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("Problem during a save of an access grant.");
        }
    }

    public AccessGrant getByClassroomIdAndTeacherId(Long classroomId, Long teacherId) {
        return repository.findByClassroomIdAndTeacherId(classroomId, teacherId);
    }

    public List<AccessGrant> getAllByClassroomId(Long classroomId){
        return repository.findAllByClassroomId(classroomId);
    }

    public List<AccessGrant> getAllByTeacherId(Long teacherId){
        return repository.findAllByTeacherId(teacherId);
    }


}