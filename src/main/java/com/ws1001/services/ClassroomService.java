package com.ws1001.services;

import com.ws1001.models.Classroom;
import com.ws1001.models.User;
import com.ws1001.repositories.ClassroomRepository;
import com.ws1001.services.exceptions.ServiceException;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.List;

@Service
public class ClassroomService extends BaseService<Classroom, ClassroomRepository> {
    @Autowired
    private JpaContext jpaContext;

    public Classroom save(Classroom model) throws ServiceException {
        if(model.getId() == null && getByName(model.getName()) != null)
            throw new ServiceException("A classroom with this name already exists!");
        else if(model.getId() != null) {
            // TO-DO: Finish proper partial update logic (shouldn't send the whole object during update)
            Classroom c = get(model.getId());
            c.setTakenKeyCount(model.getTakenKeyCount());
            return super.save(c);
        }

        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("Problem during a save of a classroom.");
        }
    }

    public Classroom getByName(String name) {
        return repository.findByName(name);
    }

    public List<Classroom> filter(String name, Integer seatCount, String equipmentName, Date free_at) {
        SessionFactory sf = jpaContext.getEntityManagerByManagedType(Classroom.class).unwrap(SessionFactory.class);
        Criteria c = sf.getCurrentSession().createCriteria(Classroom.class, "classroom");

        if(name != null) {
            c.add(Restrictions.eq("classroom.name", name));
        }

        if(seatCount != null) {
            c.add(Restrictions.ge("classroom.seatCount", seatCount));
        }

        return c.list();
    }


}