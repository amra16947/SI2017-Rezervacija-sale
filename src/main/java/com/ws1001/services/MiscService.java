package com.ws1001.services;

import com.ws1001.models.ClassroomEquipment;
import com.ws1001.repositories.ClassroomEquipmentRepository;
import com.ws1001.repositories.ClassroomRepository;
import com.ws1001.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiscService  {
    private UserRepository userRepository;

    private ClassroomRepository classroomRepository;

    private ClassroomEquipmentRepository classroomEquipmentRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setClassroomRepository(ClassroomRepository classroomRepository) { this.classroomRepository = classroomRepository; }

    @Autowired
    public void setClassroomEquipmentRepository(ClassroomEquipmentRepository classroomEquipmentRepository) { this.classroomEquipmentRepository = classroomEquipmentRepository; }

    public GlobalStats getGlobalStats() {
        return new GlobalStats(userRepository.count(), classroomRepository.count(), classroomEquipmentRepository.count());
    }

    public class GlobalStats {
        private Long userCount;
        private Long classroomCount;
        private Long equipmentCount;

        public GlobalStats() {

        }

        public GlobalStats(Long userCount, Long classroomCount, Long equipmentCount) {
            this.userCount = userCount;
            this.classroomCount = classroomCount;
            this.equipmentCount = equipmentCount;
        }

        public Long getUserCount() {
            return userCount;
        }

        public void setUserCount(Long userCount) {
            this.userCount = userCount;
        }

        public Long getClassroomCount() {
            return classroomCount;
        }

        public void setClassroomCount(Long classroomCount) {
            this.classroomCount = classroomCount;
        }

        public Long getEquipmentCount() {
            return equipmentCount;
        }

        public void setEquipmentCount(Long equipmentCount) {
            this.equipmentCount = equipmentCount;
        }
    }
}