package com.ws1001.services;

import com.ws1001.models.EquipmentType;
import com.ws1001.repositories.EquipmentTypeRepository;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Dario on 5/22/2017.
 */
@Service
public class EquipmentTypeService extends BaseService<EquipmentType, EquipmentTypeRepository> {

    public EquipmentType save(EquipmentType model) throws ServiceException {
        //Name contains just letters and numbers
        ExpressionParser parser = new SpelExpressionParser();
        boolean value = parser.parseExpression("'" + model.getName() + "' matches '^[a-zA-Z0-9]*$'").getValue(Boolean.class);

        if(model.getId() == null && getByLabel(model.getLabel()) != null)
            throw new ServiceException("Equipment type with this label already exist!");
        else if(!value)
            throw new ServiceException("Equipment type name does not have proper name!");

        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("Unknown role ID!");
        }
    }

    public EquipmentType getByLabel( int label ) { return repository.getByLabel(label); }

    public List<EquipmentType> searchByName( String name ) { return repository.searchAllByName(name); }

    public EquipmentType getFirstByLabelAndName( int equipmentTypeLabel, String equipmentTypeName) throws ServiceException {
        EquipmentType equipmentType = repository.getFirstByLabelAndName(equipmentTypeLabel, equipmentTypeName);
        if(equipmentType == null) throw new ServiceException("Equipment type does not exist!");
        return  equipmentType;
    }
}
