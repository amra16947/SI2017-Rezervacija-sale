package com.ws1001.repositories;

import com.ws1001.models.ClassroomEquipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Dario on 5/22/2017.
 */
public interface ClassroomEquipmentRepository extends PagingAndSortingRepository<ClassroomEquipment, Long> {

    @Query("select count(*) from ClassroomEquipment where classroom.id = :id and equipmentType.name = :name and equipmentType.label = :label")
    int countByNameAndLabel( @Param("name") String name, @Param ("label") int label, @Param("id") Long id);
}
