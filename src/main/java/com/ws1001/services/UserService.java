package com.ws1001.services;

import com.ws1001.models.User;
import com.ws1001.repositories.UserRepository;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User, UserRepository> {

    public User save(User model) throws ServiceException {
        // Is this a new account that is being created (id == null), and is the email or username already
        // in use?
        if(model.getId() == null && getByUsername(model.getUsername()) != null)
            throw new ServiceException("An account with this username already exists!");
        else if(model.getId() != null) {
            // Account is being updated ... username can't be changed.
            // TO-DO: Finish proper partial update logic (shouldn't send the whole object during update)
        }

        try {
            model.setPassword(BCrypt.hashpw(model.getPassword(), BCrypt.gensalt()));
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("Unknown role ID!");
        }
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<User> getByPartOfUsername(String partOfUsername) {
        return repository.findByUsernameContaining(partOfUsername);
    }

    public List<User> filter(String term) {
        return repository.filterByTerm(term);
    }

}