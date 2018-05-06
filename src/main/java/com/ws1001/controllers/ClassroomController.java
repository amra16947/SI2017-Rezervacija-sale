package com.ws1001.controllers;

import com.ws1001.controllers.forms.Classroom.ClassroomCreateForm;
import com.ws1001.controllers.forms.Classroom.ClassroomExistsForm;
import com.ws1001.models.AccessGrant;
import com.ws1001.models.Classroom;
import com.ws1001.models.User;
import com.ws1001.services.ClassroomService;
import com.ws1001.services.UserService;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ClassroomController extends BaseController<Classroom, ClassroomService> {

    @GetMapping(path = "/api/classrooms/all")
    @ResponseBody
    @Override
    public Iterable<Classroom> all() {
        return super.all();
    }

    @GetMapping(path = "/api/classrooms/{id}")
    @ResponseBody
    @Override
    public ResponseEntity get(@PathVariable("id") Long id) {
        return super.get(id);
    }

    @GetMapping(path = "/api/classrooms/page/{pageNumber}")
    @ResponseBody
    public ResponseEntity getPage(@PathVariable("pageNumber") Long id) {
        return super.get(id);
    }

    protected UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(path = "/api/classrooms")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid ClassroomCreateForm newClassroom) {
        try {
            Long teacherId = newClassroom.getTeacherId();
            User teacherInCharge = null;
            if(teacherId != null) {
                teacherInCharge = userService.get(teacherId);
                if(teacherInCharge == null || teacherInCharge.getType() != User.UserType.Teacher) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a teacher.");
                }
            }

            Classroom classroom = new Classroom(newClassroom.getName(),
                    newClassroom.getSeatCount(),
                    newClassroom.getKeyCount(),
                    Classroom.StatusType.values()[newClassroom.getStatus()],
                    Classroom.ClassroomType.values()[newClassroom.getType()]);

            classroom.setTeacherInCharge(teacherInCharge);
            classroom = service.save(classroom);

            return ResponseEntity.ok(classroom);
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/api/classrooms/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }

    @PostMapping(path = "/api/classrooms/exists")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public ResponseEntity exists(@RequestBody @Valid ClassroomExistsForm cef) {
        Boolean userExists = service.getByName(cef.getName()) != null;
        if(userExists)
            return ResponseEntity.badRequest().body(true);

        return ResponseEntity.ok(false);
    }
/*
    @ResponseBody
    p;ublic ResponseEntity filter(@PathVariable("term") String term) {
        return ResponseEntity.ok(service.filter(term));
    }
    */
}