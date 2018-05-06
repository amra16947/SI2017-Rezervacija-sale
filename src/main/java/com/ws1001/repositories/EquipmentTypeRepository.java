package com.ws1001.repositories;

import com.ws1001.models.EquipmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Dario on 5/22/2017.
 */
public interface EquipmentTypeRepository extends PagingAndSortingRepository<EquipmentType, Long> {
    EquipmentType getByLabel( int label );

    @Query("select et from EquipmentType et where et.name like  %:name%")
    List<EquipmentType> searchAllByName(@Param("name") String name );

    EquipmentType getFirstByLabelAndName(@Param("label") int Label, @Param("name") String Name);
}
