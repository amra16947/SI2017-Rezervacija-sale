package com.ws1001.controllers;

import com.ws1001.controllers.forms.AccessGrant.AccessGrantCreateForm;
import com.ws1001.models.AccessGrant;
import com.ws1001.models.Classroom;
import com.ws1001.models.User;
import com.ws1001.services.AccessGrantService;
import com.ws1001.services.ClassroomService;
import com.ws1001.services.UserService;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by amina on 22.05.2017..
 */

@RestController
public class AccessGrantController extends BaseController<AccessGrant, AccessGrantService>{
    @GetMapping(path = "/api/access-grants/all")
    @ResponseBody
    @Override
    public Iterable<AccessGrant> all() {
        return super.all();
    }

    @GetMapping(path = "/api/access-grants/{id}")
    @ResponseBody
    @Override
    public ResponseEntity get(@PathVariable("id") Long id) {
        return super.get(id);
    }

    private ClassroomService classroomService;
    private UserService userService;

    @Autowired
    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/api/access-grants")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid AccessGrantCreateForm newAccessGrant) {
        try {
            Classroom classroom = classroomService.get(newAccessGrant.getClassroomId());
            User teacher = userService.get(newAccessGrant.getUserId());

            if(classroom == null || teacher == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong classroom or teacher ID.");
            }

            return ResponseEntity.ok(service.save(new AccessGrant(classroom, teacher)));

        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/api/access-grants/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }

    @GetMapping(path = "/api/access-grants/classroom/{id}")
    @ResponseBody
    public ResponseEntity getAllByClassroomId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getAllByClassroomId(id));
    }

    @GetMapping(path = "/api/access-grants/teacher/{id}")
    @ResponseBody
    public ResponseEntity getAllByTeacherId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getAllByTeacherId(id));
    }


}
