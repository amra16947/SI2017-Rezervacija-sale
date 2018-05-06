package com.ws1001.controllers;

import com.ws1001.controllers.forms.User.UserCreateForm;
import com.ws1001.models.User;
import com.ws1001.services.UserService;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController extends BaseController<User, UserService> {
    @GetMapping(path = "/api/users/all")
    @ResponseBody
    @Override
    public Iterable<User> all() {
        return super.all();
    }



    @PostMapping(path = "/api/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid UserCreateForm newUser) {
        try {
            if(newUser.getType() == 2) {
                throw new ServiceException("Ne moze se kreirati korisnik sa rolom admina.");
            }
            
            User user = new User(newUser.getFirstName(),
                    newUser.getLastName(),
                    newUser.getUsername(),
                    newUser.getPassword(),
                    User.UserType.values()[newUser.getType()]);

            user = service.save(user);
            return ResponseEntity.ok(user);
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/api/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }

    @GetMapping(path = "/api/users/filter/{term}")
    @ResponseBody
    public ResponseEntity filter(@PathVariable("term") String term) {
        return ResponseEntity.ok(service.filter(term));
    }

    @GetMapping(path = "/api/users/exists/{username}")
    @ResponseBody
    public ResponseEntity exists(@PathVariable("username") String username) {
        Boolean userExists = service.getByUsername(username) != null;
        if(userExists)
            return ResponseEntity.badRequest().body(true);

        return ResponseEntity.ok(false);
    }

    @GetMapping(path = "/api/users/page/{pageNumber}")
    @ResponseBody
    public ResponseEntity getPage(@PathVariable("pageNumber") int pageNumber)
    {
        return super.getPage(pageNumber);
    }
}