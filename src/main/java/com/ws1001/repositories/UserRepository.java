package com.ws1001.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.ws1001.models.User;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameContaining(String partOfUsername);

    @Query("Select u from User u where u.username like %?1% or u.firstName like %?1% or u.lastName like %?1%")
    List<User> filterByTerm(String term);
}