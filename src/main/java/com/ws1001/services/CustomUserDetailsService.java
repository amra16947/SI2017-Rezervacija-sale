package com.ws1001.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import com.ws1001.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    static Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.ws1001.models.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        logger.info(user.getUsername() + " " + user.getPassword() + " " + getGrantedAuthorities(user)
                + "/" + user.getRole());

        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(com.ws1001.models.User user) {

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user.getRole() != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        }
        return grantedAuthorities;
    }
}