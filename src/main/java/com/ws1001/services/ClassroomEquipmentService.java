package com.ws1001.services;

import com.ws1001.models.ClassroomEquipment;
import com.ws1001.repositories.ClassroomEquipmentRepository;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.stereotype.Service;

/**
 * Created by Dario on 5/22/2017.
 */
@Service
public class ClassroomEquipmentService extends BaseService<ClassroomEquipment, ClassroomEquipmentRepository> {

    public ClassroomEquipment save(ClassroomEquipment model) throws ServiceException {

        if(model.getClassroom() == null)
            throw new ServiceException("Classroom does not exist!");
        else if (model.getEquipmentType() == null)
            throw new ServiceException("Equipment type does not exist!");
        if(repository.countByNameAndLabel(model.getEquipmentType().getName(), model.getEquipmentType().getLabel(), model.getClassroom().getId()) > 0)
            throw new ServiceException("Equipment already exists in classroom!");

        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("");
        }
    }
}
